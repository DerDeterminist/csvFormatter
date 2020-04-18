package conts;

public class OutputConfig
{
   private String separator;

   public String getSeparator()
   {
      return separator;
   }

   public void setSeparator(String separator)
   {
      this.separator = separator;
   }

   @Override
   public String toString()
   {
      return "OutputConfig{" +
            "separator='" + separator + '\'' +
            '}';
   }
}
