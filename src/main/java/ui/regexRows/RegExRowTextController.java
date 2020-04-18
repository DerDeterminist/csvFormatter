package ui.regexRows;

import conts.Condition;
import conts.Match;
import ui.components.RegexRowController;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static conts.MatchType.PLAN_TEXT;

public class RegExRowTextController extends RegexRowController<RegExRowTextView>
{
   public RegExRowTextController(BiConsumer<Match, Condition> matchConsumer)
   {
      super(new RegExRowTextView(), matchConsumer);
   }

   @Override
   protected void resetComponents()
   {
      getView().getTextField().setText("");
   }

   @Override
   public void initData(List<Integer> columns)
   {
      //noop
   }

   @Override
   public Supplier<Optional<Match>> getMatchSupplier()
   {
      return () -> {
         String text = getView().getTextField().getText();
         if (text != null && !text.isEmpty())
         {
            Match match = new Match(PLAN_TEXT);
            match.setText(text);
            return Optional.of(match);
         }
         return Optional.empty();
      };
   }
}
