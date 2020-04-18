package ui.condtionRows;

import ui.components.ConditionRowView;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.util.Arrays;
import java.util.List;

public class ConditionHasPreviousView extends ConditionRowView
{
   private JLabel hasPreviousLabel = new JLabel("has previous");
   private JCheckBox hasPrevious = new JCheckBox();

   @Override
   protected List<JComponent> getExtraComponents()
   {
      return Arrays.asList(hasPrevious, hasPreviousLabel);
   }

   public JCheckBox getHasPrevious()
   {
      return hasPrevious;
   }
}
