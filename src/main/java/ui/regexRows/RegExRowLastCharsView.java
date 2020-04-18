package ui.regexRows;

import ui.Constants;
import ui.components.RegExRowView;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;

public class RegExRowLastCharsView extends RegExRowView
{
   private JComboBox<Integer> column = new JComboBox<>();
   private JLabel countOfLabel = new JLabel("count of last chars");
   private JTextField count = new JTextField();

   RegExRowLastCharsView()
   {
      initComponents();
   }

   private void initComponents()
   {
      column.setPreferredSize(Constants.BTN_DIMENSION);
      count.setPreferredSize(new Dimension(62, Constants.COMPONENT_HEIGHT));
   }

   @Override
   protected List<JComponent> getExtraComponents()
   {
      return Arrays.asList(column, countOfLabel, count);
   }

   JComboBox<Integer> getColumn()
   {
      return column;
   }

   JTextField getCount()
   {
      return count;
   }
}
