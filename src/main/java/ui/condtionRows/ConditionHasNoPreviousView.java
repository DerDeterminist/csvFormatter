package ui.condtionRows;

import ui.components.ConditionRowView;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.util.Arrays;
import java.util.List;

public class ConditionHasNoPreviousView extends ConditionRowView
{
   private JLabel hasNoPreviousLabel = new JLabel("has no previous");
   private JCheckBox hasNoPrevious = new JCheckBox();

   @Override
   protected List<JComponent> getExtraComponents()
   {
      return Arrays.asList(hasNoPrevious, hasNoPreviousLabel);
   }

   public JCheckBox getHasNoPrevious()
   {
      return hasNoPrevious;
   }
}
