package conts;

import java.util.ArrayList;
import java.util.List;

public class PatternEntity
{
   private List<Pattern> pattern = new ArrayList<>();

   public List<Pattern> getPattern()
   {
      return pattern;
   }

   public void setPattern(List<Pattern> pattern)
   {
      this.pattern = pattern;
   }

   @Override
   public String toString()
   {
      return "PatternEntity{" +
            "pattern=" + pattern +
            '}';
   }
}
