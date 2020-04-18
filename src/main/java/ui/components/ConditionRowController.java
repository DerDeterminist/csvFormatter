package ui.components;

import conts.Condition;

import java.util.Optional;

public abstract class ConditionRowController<T extends ConditionRowView>
{
   private T view;

   public ConditionRowController(T view)
   {
      this.view = view;
      view.initUI();
   }

   public final T getView()
   {
      return view;
   }

   public abstract Optional<Condition> getCondition();
}
