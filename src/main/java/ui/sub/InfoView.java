package ui.sub;

import services.IconService;
import services.StyleService;
import ui.components.JLabelLink;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.Arrays;

public class InfoView extends JDialog
{
   private JLabelLink logos = new JLabelLink("logos", "https://icon-icons.com/pack/Material-Design-Icons/259");
   private JLabelLink gson = new JLabelLink("gson", "https://github.com/google/gson");
   private JLabelLink migLayout = new JLabelLink("miglayout", "http://miglayout.com/");
   private JButton help = new JButton(IconService.getImageIcon(IconService.IconType.INFO, 30, 30));
   private JButton logs = new JButton(IconService.getImageIcon(IconService.IconType.FOLDER, 30, 30));
   private JPanel panel = new JPanel();

   InfoView(Frame owner)
   {
      super(owner);
      initDialog();
      initComponents();
      initLayout();
      StyleService
            .styleComponents(Arrays.stream(getComponents()).map(component -> ((JComponent) component)).toArray(JComponent[]::new));
   }

   private void initLayout()
   {
      panel.add(logos);
      panel.add(gson);
      panel.add(migLayout);
      panel.add(Box.createRigidArea(new Dimension(0, 10)));
      panel.add(help);
      panel.add(Box.createRigidArea(new Dimension(0, 5)));
      panel.add(logs);

      panel.add(Box.createRigidArea(new Dimension(5, 0)));

      add(panel);
   }

   private void initComponents()
   {
      help.setPreferredSize(new Dimension(43, 40));
      logs.setPreferredSize(new Dimension(43, 40));
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
   }

   private void initDialog()
   {
      setModal(true);
      setTitle("info");
      setPreferredSize(new Dimension(300, 350));
      setMinimumSize(new Dimension(300, 350));

      setIconImage(IconService.getImageIcon(IconService.IconType.LOGO, 120, 120).getImage());
      SwingUtilities.invokeLater(() -> {
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
      });
   }

   JButton getLogs()
   {
      return logs;
   }

   JButton getHelp()
   {
      return help;
   }
}
