package ui.sub;

import net.miginfocom.swing.MigLayout;
import services.IconService;
import services.StyleService;
import ui.Constants;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.function.Consumer;

public class OutputView extends JDialog
{
   private JButton ok = new JButton(IconService.getImageIcon(IconService.IconType.YES, 30, 30));
   private JButton cancel = new JButton(IconService.getImageIcon(IconService.IconType.NO, 30, 30));
   private JPanel controlPanel = new JPanel();

   private JPanel root = new JPanel();

   private JLabel separatorLabel = new JLabel("separator");
   private JComboBox<String> separator = new JComboBox<>();

   OutputView(Frame owner, Consumer<WindowEvent> windowEventConsumer)
   {
      super(owner);
      initDialog(windowEventConsumer);
      initComponents();
      initLayout();
   }

   private void initDialog(Consumer<WindowEvent> windowEventConsumer)
   {
      addWindowListener(new WindowAdapter()
      {
         @Override
         public void windowClosing(WindowEvent windowEvent)
         {
            windowEventConsumer.accept(windowEvent);
         }
      });
      setModal(true);
      setTitle("export");
      setPreferredSize(new Dimension(350, 400));
      setMinimumSize(new Dimension(350, 400));

      setIconImage(IconService.getImageIcon(IconService.IconType.LOGO, 120, 120).getImage());
      SwingUtilities.invokeLater(() -> {
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
      });
   }

   private void initComponents()
   {
      ok.setMaximumSize(Constants.BTN_DIMENSION);
      ok.setMinimumSize(Constants.BTN_DIMENSION);
      cancel.setMaximumSize(Constants.BTN_DIMENSION);
      cancel.setMinimumSize(Constants.BTN_DIMENSION);
   }

   private void initLayout()
   {
      root.setLayout(new MigLayout());
      controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
      controlPanel.setPreferredSize(new Dimension(Constants.COMPONENT_WIGHT * 2, Constants.COMPONENT_HEIGHT));

      controlPanel.add(ok);
      controlPanel.add(cancel);

      root.add(separator);
      root.add(separatorLabel, "wrap");

      root.add(controlPanel);

      add(root);

      StyleService
            .styleComponents(Arrays.stream(getComponents()).map(component -> ((JComponent) component)).toArray(JComponent[]::new));
   }

   JButton getOk()
   {
      return ok;
   }

   JButton getCancel()
   {
      return cancel;
   }

   JComboBox<String> getSeparator()
   {
      return separator;
   }
}
