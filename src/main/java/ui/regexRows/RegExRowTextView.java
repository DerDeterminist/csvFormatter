package ui.regexRows;

import ui.Constants;
import ui.components.RegExRowView;

import javax.swing.JComponent;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

public class RegExRowTextView extends RegExRowView
{
   private JTextField textField = new JTextField();

   RegExRowTextView()
   {
      initComponents();
   }

   private void initComponents()
   {
      textField.setPreferredSize(new Dimension(260, Constants.COMPONENT_HEIGHT));
   }

   @Override
   protected List<JComponent> getExtraComponents()
   {
      return Collections.singletonList(textField);
   }

   JTextField getTextField()
   {
      return textField;
   }
}
