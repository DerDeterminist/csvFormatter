package ui.regexRows;

import conts.Condition;
import conts.Match;
import conts.MatchType;
import ui.components.RegexRowController;

import javax.swing.DefaultComboBoxModel;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class RegExRowAfterController extends RegexRowController<RegExRowAfterView>
{
   public RegExRowAfterController(BiConsumer<Match, Condition> matchConsumer)
   {
      super(new RegExRowAfterView(), matchConsumer);
   }

   @Override
   public void initData(List<Integer> columns)
   {
      DefaultComboBoxModel<Integer> model = (DefaultComboBoxModel<Integer>) getView().getColumn().getModel();
      columns.forEach(model::addElement);
   }

   @Override
   protected void resetComponents()
   {
      getView().getColumn().setSelectedIndex(0);
      getView().getText().setText("");
   }

   @Override
   public Supplier<Optional<Match>> getMatchSupplier()
   {
      return () -> {
         String text = getView().getText().getText();
         Integer selectedItem = (Integer) getView().getColumn().getSelectedItem();
         if (text != null && !text.isEmpty() && selectedItem != null)
         {
            Match match = new Match(MatchType.AFTER_TEXT);
            match.setText(text);
            match.setColumn(selectedItem);
            return Optional.of(match);
         }
         return Optional.empty();
      };
   }
}
