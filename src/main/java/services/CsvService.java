package services;

import conts.InputConfig;
import conts.OutputConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvService
{
   public static List<List<String>> read(File file, InputConfig config)
   {
      try (Stream<String> stream = Files.lines(Paths.get(file.toURI())))
      {
         return stream.map(s -> {
            if (config.isRemoveQuotes())
            {
               return s.replace("\"", "");
            }
            if (config.isRemoveSingleQuotes())
            {
               return s.replace("'", "");
            }
            return s;
         }).map(s -> Arrays.asList(s.split(config.getSeparator()))).collect(Collectors.toList());
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
   }

   public static void write(File file, List<List<String>> values, OutputConfig config)
   {
      try (BufferedWriter writer = Files.newBufferedWriter(file.toPath()))
      {
         writer.write(values.stream()
               .map(strings -> String.join(config.getSeparator(), strings))
               .collect(Collectors.joining(System.lineSeparator())));
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
   }
}
