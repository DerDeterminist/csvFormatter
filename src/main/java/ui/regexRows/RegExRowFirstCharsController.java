package ui.regexRows;

import conts.Condition;
import conts.Match;
import conts.MatchType;
import ui.components.OnlyNumbersDocumentFilter;
import ui.components.RegexRowController;

import javax.swing.DefaultComboBoxModel;
import javax.swing.text.PlainDocument;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class RegExRowFirstCharsController extends RegexRowController<RegExRowFirstCharsView>
{
   public RegExRowFirstCharsController(BiConsumer<Match, Condition> matchConsumer)
   {
      super(new RegExRowFirstCharsView(), matchConsumer);
      initListeners();
   }

   private void initListeners()
   {
      ((PlainDocument) getView().getCount().getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
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
      getView().getCount().setText("");
   }

   @Override
   public Supplier<Optional<Match>> getMatchSupplier()
   {
      return () -> {
         Object selectedItem = getView().getColumn().getSelectedItem();
         String text = getView().getCount().getText();
         if (selectedItem != null && text != null && !text.isEmpty())
         {
            Match match = new Match(MatchType.FIST_CHARS);
            try
            {
               match.setChars(Integer.valueOf(text));
            }
            catch (NumberFormatException e)
            {
               e.printStackTrace();
            }
            match.setColumn((Integer) selectedItem);

            return Optional.of(match);
         }
         return Optional.empty();
      };
   }
}
