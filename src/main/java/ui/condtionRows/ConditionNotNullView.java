package ui.condtionRows;

import ui.components.ConditionRowView;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.util.Arrays;
import java.util.List;

public class ConditionNotNullView extends ConditionRowView
{
   private JLabel notNullLabel = new JLabel("not null");
   private JCheckBox notNull = new JCheckBox();

   @Override
   protected List<JComponent> getExtraComponents()
   {
      return Arrays.asList(notNull, notNullLabel);
   }

   public JCheckBox getNotNull()
   {
      return notNull;
   }
}
