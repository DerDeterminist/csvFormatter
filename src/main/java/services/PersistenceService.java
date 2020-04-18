package services;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersistenceService
{
   private static Gson gson = new Gson();

   public static <T> T load(Class<T> clas, String fileName)
   {
      try (Stream<String> stream = Files.lines(Paths.get(new File(fileName).toURI())))
      {
         return gson.fromJson(stream.collect(Collectors.joining()), clas);
      }
      catch (NoSuchFileException e)
      {
         return null;
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
   }

   public static void save(Object object, String fileName)
   {
      try (BufferedWriter writer = Files.newBufferedWriter(new File(fileName).toPath()))
      {
         gson.toJson(object, writer);
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
   }
}
