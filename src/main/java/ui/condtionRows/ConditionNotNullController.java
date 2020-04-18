package ui.condtionRows;

import conts.Condition;
import conts.ConditionType;
import ui.components.ConditionRowController;

import java.util.Optional;

public class ConditionNotNullController extends ConditionRowController<ConditionNotNullView>
{
   public ConditionNotNullController()
   {
      super(new ConditionNotNullView());
   }

   @Override
   public Optional<Condition> getCondition()
   {
      boolean notNull = getView().getNotNull().isSelected();
      if (notNull)
      {
         Condition condition = new Condition(ConditionType.NOT_NULL);
         return Optional.of(condition);
      }
      return Optional.empty();
   }
}
