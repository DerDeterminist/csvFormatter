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

public class RegExRowBetweenController extends RegexRowController<RegExRowBetweenView>
{
   public RegExRowBetweenController(BiConsumer<Match, Condition> matchConsumer)
   {
      super(new RegExRowBetweenView(), matchConsumer);
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
      getView().getText1().setText("");
      getView().getText2().setText("");
   }

   @Override
   public Supplier<Optional<Match>> getMatchSupplier()
   {
      return () -> {
         String text = getView().getText1().getText();
         String text1 = getView().getText2().getText();
         Integer selectedItem = (Integer) getView().getColumn().getSelectedItem();
         if (text != null && !text.isEmpty() && text1 != null && !text1.isEmpty() && selectedItem != null)
         {
            Match match = new Match(MatchType.BETWEEN_TEXT);
            match.setText(text);
            match.setText1(text1);
            match.setColumn(selectedItem);
            return Optional.of(match);
         }
         return Optional.empty();
      };
   }
}
