package ui.components;

import net.miginfocom.swing.MigLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.List;

public abstract class ConditionRowView extends JPanel
{
   void initUI()
   {
      List<JComponent> extraComponents = getExtraComponents();
      initLayout(extraComponents);
   }

   protected abstract List<JComponent> getExtraComponents();

   private void initLayout(List<JComponent> extraComponents)
   {
      setLayout(new MigLayout());
      extraComponents.forEach(this::add);
   }
}
