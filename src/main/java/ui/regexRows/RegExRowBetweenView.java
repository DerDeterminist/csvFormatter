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

public class RegExRowBetweenView extends RegExRowView
{
   private JComboBox<Integer> column = new JComboBox<>();
   private JLabel before = new JLabel("between");
   private JTextField text1 = new JTextField();
   private JLabel and = new JLabel("and");
   private JTextField text2 = new JTextField();

   RegExRowBetweenView()
   {
      initComponents();
   }

   private void initComponents()
   {
      column.setPreferredSize(Constants.BTN_DIMENSION);
      text1.setPreferredSize(new Dimension(49, Constants.COMPONENT_HEIGHT));
      text2.setPreferredSize(new Dimension(49, Constants.COMPONENT_HEIGHT));
   }

   @Override
   protected List<JComponent> getExtraComponents()
   {
      return Arrays.asList(column, before, text1, and, text2);
   }

   JComboBox<Integer> getColumn()
   {
      return column;
   }

   JTextField getText1()
   {
      return text1;
   }

   JTextField getText2()
   {
      return text2;
   }
}
