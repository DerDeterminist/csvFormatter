package ui.components;

import net.miginfocom.swing.MigLayout;
import services.IconService;
import ui.Constants;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class RegExRowView extends JPanel
{
   private JButton add = new JButton(IconService.getImageIcon(IconService.IconType.ADD, 30, 30));
   private JButton condition = new JButton(IconService.getImageIcon(IconService.IconType.WHEN, 30, 30));

   void initUI()
   {
      List<JComponent> extraComponents = getExtraComponents();
      initComponents();
      initLayout(extraComponents);
   }

   final void addConditionListener(ActionListener listener)
   {
      condition.addActionListener(listener);
   }

   final void addAddListener(ActionListener listener)
   {
      add.addActionListener(listener);
   }

   protected abstract List<JComponent> getExtraComponents();

   private void initComponents()
   {
      add.setMinimumSize(Constants.BTN_DIMENSION);
      add.setMaximumSize(Constants.BTN_DIMENSION);
      condition.setMinimumSize(Constants.BTN_DIMENSION);
      condition.setMaximumSize(Constants.BTN_DIMENSION);
   }

   private void initLayout(List<JComponent> extraComponents)
   {
      setLayout(new MigLayout());
      extraComponents.forEach(jComponent -> add(jComponent, "right"));
      add(condition, "right", -1);// -1 -> at the end
      add(add, "right", -1);
   }
}
