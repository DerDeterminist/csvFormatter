package conts;

import java.util.ArrayList;
import java.util.List;

public class CsvFile
{
   private List<List<String>> columns = new ArrayList<>();

   public List<List<String>> getColumns()
   {
      return columns;
   }

   public void setColumns(List<List<String>> columns)
   {
      this.columns = columns;
   }

   @Override
   public String toString()
   {
      return "CsvFile{" +
            "Columns=" + columns +
            '}';
   }
}
