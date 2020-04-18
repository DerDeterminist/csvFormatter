package ui.components;

import conts.Condition;
import conts.Match;
import ui.sub.ConditionController;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public abstract class RegexRowController<T extends RegExRowView>
{
   private T view;
   private Condition condition;

   public RegexRowController(T view, BiConsumer<Match, Condition> consumer)
   {
      this.view = view;
      view.addAddListener(actionEvent -> getMatchSupplier().get().ifPresent(match -> {
         consumer.accept(match, condition);
         resetComponents();
         condition = null;
      }));

      view.addConditionListener(actionEvent ->
            new ConditionController(
                  optionalCondition -> optionalCondition.ifPresent(condition -> RegexRowController.this.condition = condition)));
      view.initUI();
   }

   protected abstract void resetComponents();

   public abstract void initData(List<Integer> columns);

   public final T getView()
   {
      return view;
   }

   public abstract Supplier<Optional<Match>> getMatchSupplier();
}
