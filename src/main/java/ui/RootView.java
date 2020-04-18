package ui;

import services.IconService;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import static services.StyleService.styleComponents;

public class RootView extends JFrame
{
   private JSplitPane leftOuter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
   private JSplitPane centerRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

   public RootView()
   {
      initComponents();
      initLayout();
   }

   private void initLayout()
   {
      leftOuter.add(centerRight, JSplitPane.RIGHT);
      add(leftOuter);
   }

   private void initComponents()
   {
      setIconImage(IconService.getImageIcon(IconService.IconType.LOGO, 120, 120).getImage());
      setPreferredSize(new Dimension(1350, 800));
      setMinimumSize(new Dimension(900, 800));
      SwingUtilities.invokeLater(() -> {
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
      });

      setTitle("csv formatter");
      styleComponents(leftOuter, centerRight, this);
   }

   public void setLeft(Component component)
   {
      leftOuter.add(component, JSplitPane.LEFT);
   }

   public void setCenter(Component component)
   {
      centerRight.add(component, JSplitPane.LEFT);
   }

   public void setRight(Component component)
   {
      centerRight.add(component, JSplitPane.RIGHT);
   }
}
