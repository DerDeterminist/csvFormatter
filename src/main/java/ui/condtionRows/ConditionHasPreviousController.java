package ui.condtionRows;

import conts.Condition;
import conts.ConditionType;
import ui.components.ConditionRowController;

import java.util.Optional;

public class ConditionHasPreviousController extends ConditionRowController<ConditionHasPreviousView>
{
   public ConditionHasPreviousController()
   {
      super(new ConditionHasPreviousView());
   }

   @Override
   public Optional<Condition> getCondition()
   {
      boolean hasPrevious = getView().getHasPrevious().isSelected();
      if (hasPrevious)
      {
         Condition condition = new Condition(ConditionType.HAS_PREVIOUS);
         return Optional.of(condition);
      }
      return Optional.empty();
   }
}
