package ui.sub;

import net.miginfocom.swing.MigLayout;
import services.IconService;
import services.StyleService;
import ui.Constants;
import ui.components.RegExRowView;
import ui.components.TableModel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CustomRegExView extends JDialog
{
   private JSplitPane root = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

   private JTable prev = new JTable();
   private JScrollPane prevScrollPane = new JScrollPane(prev);

   private JButton ok = new JButton(IconService.getImageIcon(IconService.IconType.YES, 30, 30));
   private JButton cancel = new JButton(IconService.getImageIcon(IconService.IconType.NO, 30, 30));
   private JPanel controlPanel = new JPanel();

   private JPanel details = new JPanel();

   CustomRegExView(List<RegExRowView> regExRowViews, Frame frame, Consumer<WindowEvent> windowsEventConsumer)
   {
      super(frame);
      initDialog(windowsEventConsumer);
      initComponents();
      initTable();
      initLayout(regExRowViews);
   }

   private void initDialog(Consumer<WindowEvent> windowsEventConsumer)
   {
      addWindowListener(new WindowAdapter()
      {
         @Override
         public void windowClosing(WindowEvent windowEvent)
         {
            windowsEventConsumer.accept(windowEvent);
         }
      });
      setModal(true);
      setTitle("custom column");
      setPreferredSize(new Dimension(900, 600));
      setMinimumSize(new Dimension(900, 600));

      setIconImage(IconService.getImageIcon(IconService.IconType.LOGO, 120, 120).getImage());
      SwingUtilities.invokeLater(() -> {
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
      });
   }

   private void initTable()
   {
      prev.setModel(new TableModel<List<String>>());
   }

   private void initComponents()
   {
      ok.setMaximumSize(Constants.BTN_DIMENSION);
      ok.setMinimumSize(Constants.BTN_DIMENSION);
      cancel.setMaximumSize(Constants.BTN_DIMENSION);
      cancel.setMinimumSize(Constants.BTN_DIMENSION);

      details.setMaximumSize(new Dimension(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE)));
      details.setMinimumSize(new Dimension(new Dimension(380, Integer.MAX_VALUE)));

      prev.setPreferredScrollableViewportSize(new Dimension(235, 200));
      prev.setFillsViewportHeight(true);
   }

   private void initLayout(List<RegExRowView> regExRowViews)
   {
      details.setLayout(new MigLayout());
      controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
      controlPanel.setPreferredSize(new Dimension(Constants.COMPONENT_WIGHT * 2, Constants.COMPONENT_HEIGHT));

      root.add(prevScrollPane, JSplitPane.RIGHT);
      root.add(details, JSplitPane.LEFT);

      regExRowViews.forEach(regexRowView -> details.add(regexRowView, "wrap, right"));
      controlPanel.add(ok);
      controlPanel.add(cancel);

      details.add(controlPanel, "center");

      add(root);

      StyleService
            .styleComponents(Arrays.stream(getComponents()).map(component -> ((JComponent) component)).toArray(JComponent[]::new));
   }

   JTable getPrev()
   {
      return prev;
   }

   TableModel<List<String>> getTableModel()
   {
      //noinspection unchecked
      return (TableModel<List<String>>) prev.getModel();
   }

   JButton getOk()
   {
      return ok;
   }

   JButton getCancel()
   {
      return cancel;
   }
}
