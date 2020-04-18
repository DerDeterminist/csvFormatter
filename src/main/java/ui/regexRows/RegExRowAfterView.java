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

public class RegExRowAfterView extends RegExRowView
{
   private JComboBox<Integer> column = new JComboBox<>();
   private JLabel after = new JLabel("  after  ");
   private JTextField text = new JTextField();

   RegExRowAfterView()
   {
      initComponents();
   }

   private void initComponents()
   {
      text.setPreferredSize(new Dimension(155, Constants.COMPONENT_HEIGHT));
      column.setPreferredSize(Constants.BTN_DIMENSION);
   }

   @Override
   protected List<JComponent> getExtraComponents()
   {
      return Arrays.asList(column, after, text);
   }

   JComboBox<Integer> getColumn()
   {
      return column;
   }

   JTextField getText()
   {
      return text;
   }
}
