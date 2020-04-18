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

public class RegExRowLastCharsController extends RegexRowController<RegExRowLastCharsView>
{
   public RegExRowLastCharsController(BiConsumer<Match, Condition> matchConsumer)
   {
      super(new RegExRowLastCharsView(), matchConsumer);
      initListeners();
   }

   private void initListeners()
   {
      ((PlainDocument) getView().getCount().getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
   }

   @Override
   protected void resetComponents()
   {
      getView().getColumn().setSelectedIndex(0);
      getView().getCount().setText("");
   }

   @Override
   public void initData(List<Integer> columns)
   {
      DefaultComboBoxModel<Integer> model = (DefaultComboBoxModel<Integer>) getView().getColumn().getModel();
      columns.forEach(model::addElement);
   }

   @Override
   public Supplier<Optional<Match>> getMatchSupplier()
   {
      return () -> {
         Object selectedItem = getView().getColumn().getSelectedItem();
         String text = getView().getCount().getText();
         if (selectedItem != null && text != null && !text.isEmpty())
         {
            Match match = new Match(MatchType.LAST_CHARS);
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
