package conts;

public class InputConfig
{
   private String separator;
   private boolean removeQuotes;
   private boolean removeSingleQuotes;

   public String getSeparator()
   {
      return separator;
   }

   public void setSeparator(String separator)
   {
      this.separator = separator;
   }

   public boolean isRemoveQuotes()
   {
      return removeQuotes;
   }

   public void setRemoveQuotes(boolean removeQuotes)
   {
      this.removeQuotes = removeQuotes;
   }

   public boolean isRemoveSingleQuotes()
   {
      return removeSingleQuotes;
   }

   public void setRemoveSingleQuotes(boolean removeSingleQuotes)
   {
      this.removeSingleQuotes = removeSingleQuotes;
   }

   @Override
   public String toString()
   {
      return "InputConfig{" +
            "separator='" + separator + '\'' +
            ", removeQuotes=" + removeQuotes +
            ", removeSingleQuotes=" + removeSingleQuotes +
            '}';
   }
}
