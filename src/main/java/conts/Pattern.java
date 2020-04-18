package conts;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Pattern
{
   private String name;
   private List<Match> matches = new ArrayList<>();

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public List<Match> getMatches()
   {
      return matches;
   }

   public void setMatches(List<Match> matches)
   {
      this.matches = matches;
   }

   @Override
   public String toString()
   {
      return "Pattern{" +
            "name='" + name + '\'' +
            ", matches=" + matches +
            '}';
   }
}
