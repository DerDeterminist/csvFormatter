package ui.regexRows;

import ui.Constants;
import ui.components.RegExRowView;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.util.Collections;
import java.util.List;

public class RegExRowColumnView extends RegExRowView
{
   private JComboBox<Integer> columnChooser = new JComboBox<>();

   RegExRowColumnView()
   {
      initComponents();
   }

   private void initComponents()
   {
      columnChooser.setPreferredSize(Constants.BTN_DIMENSION);
   }

   @Override
   protected List<JComponent> getExtraComponents()
   {
      return Collections.singletonList(columnChooser);
   }

   JComboBox<Integer> getColumnChooser()
   {
      return columnChooser;
   }
}
