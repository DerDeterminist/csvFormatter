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

public class RegExRowBeforeView extends RegExRowView
{
   private JComboBox<Integer> column = new JComboBox<>();
   private JLabel before = new JLabel("before");
   private JTextField text = new JTextField();

   RegExRowBeforeView()
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
      return Arrays.asList(column, before, text);
   }

   JTextField getText()
   {
      return text;
   }

   JComboBox<Integer> getColumn()
   {
      return column;
   }
}
