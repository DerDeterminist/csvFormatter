package conts;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("unused")
public class Condition
{
   private ConditionType type;
   private Collection<Condition> subConditions = new ArrayList<>();

   public Condition(ConditionType type)
   {
      this.type = type;
   }

   public boolean isValid(String possibleResult, String buildingResult)
   {
      switch (type)
      {
         case HOLDER:
            return subConditions.stream().allMatch(condition -> condition.isValid(possibleResult, buildingResult));
         case NOT_NULL:
            return possibleResult != null;
         case HAS_PREVIOUS:
            return buildingResult != null && !buildingResult.isEmpty();
         case HAS_NO_PREVIOUS:
            return buildingResult == null || buildingResult.isEmpty();
         default:
            throw new RuntimeException("not implement");
      }
   }

   public ConditionType getType()
   {
      return type;
   }

   public void setType(ConditionType type)
   {
      this.type = type;
   }

   public Collection<Condition> getSubConditions()
   {
      return subConditions;
   }

   public void setSubConditions(Collection<Condition> subConditions)
   {
      this.subConditions = subConditions;
   }

   @Override
   public String toString()
   {
      return "Condition{" +
            "type=" + type +
            ", subConditions=" + subConditions +
            '}';
   }
}
