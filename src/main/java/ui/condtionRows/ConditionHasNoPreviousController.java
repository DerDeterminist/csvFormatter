package ui.condtionRows;

import conts.Condition;
import conts.ConditionType;
import ui.components.ConditionRowController;

import java.util.Optional;

public class ConditionHasNoPreviousController extends ConditionRowController<ConditionHasNoPreviousView>
{
   public ConditionHasNoPreviousController()
   {
      super(new ConditionHasNoPreviousView());
   }

   @Override
   public Optional<Condition> getCondition()
   {
      boolean hasPrevious = getView().getHasNoPrevious().isSelected();
      if (hasPrevious)
      {
         Condition condition = new Condition(ConditionType.HAS_NO_PREVIOUS);
         return Optional.of(condition);
      }
      return Optional.empty();
   }
}
