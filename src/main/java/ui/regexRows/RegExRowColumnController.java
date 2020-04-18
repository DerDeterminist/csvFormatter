package ui.regexRows;

import conts.Condition;
import conts.Match;
import ui.components.RegexRowController;

import javax.swing.DefaultComboBoxModel;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static conts.MatchType.COLUMN;

public class RegExRowColumnController extends RegexRowController<RegExRowColumnView>
{

   public RegExRowColumnController(BiConsumer<Match, Condition> matchConsumer)
   {
      super(new RegExRowColumnView(), matchConsumer);
   }

   @Override
   public void initData(List<Integer> columns)
   {
      DefaultComboBoxModel<Integer> model = (DefaultComboBoxModel<Integer>) getView().getColumnChooser().getModel();
      columns.forEach(model::addElement);
   }

   @Override
   protected void resetComponents()
   {
      getView().getColumnChooser().setSelectedIndex(0);
   }

   @Override
   public Supplier<Optional<Match>> getMatchSupplier()
   {
      return () -> {
         Integer selectedItem = (Integer) getView().getColumnChooser().getSelectedItem();
         if (selectedItem != null)
         {
            Match match = new Match(COLUMN);
            match.setColumn(selectedItem);
            return Optional.of(match);
         }
         return Optional.empty();
      };
   }
}
