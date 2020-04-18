package ui;

import services.IconService;
import services.StyleService;
import ui.components.TableModel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class PrevView extends JPanel
{
   private JButton custom = new JButton(IconService.getImageIcon(IconService.IconType.CUSTOM, 30, 30));

   private JComboBox<Integer> columnChooser = new JComboBox<>();
   private JButton addColumn = new JButton(IconService.getImageIcon(IconService.IconType.ADD, 30, 30));
   private JButton removeColumn = new JButton(IconService.getImageIcon(IconService.IconType.REMOVE, 30, 30));

   private JButton export = new JButton(IconService.getImageIcon(IconService.IconType.EXPORT, 30, 30));
   private JButton info = new JButton(IconService.getImageIcon(IconService.IconType.INFO, 30, 30));

   private JPanel header = new JPanel();

   private JTable prev = new JTable();
   private JScrollPane prevScrollPane = new JScrollPane(prev);

   public PrevView()
   {
      initComponents();
      initTable();
      initLayout();
   }

   private void initComponents()
   {
      addColumn.setPreferredSize(new Dimension(43, 40));
      removeColumn.setPreferredSize(new Dimension(43, 40));
      export.setPreferredSize(new Dimension(43, 40));
      custom.setPreferredSize(new Dimension(43, 40));
      info.setPreferredSize(new Dimension(43, 40));

      columnChooser.setPreferredSize(new Dimension(43, 40));

      header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
      header.setMinimumSize(new Dimension(550, 60));

      prev.setPreferredScrollableViewportSize(new Dimension(235, 200));
      prev.setFillsViewportHeight(true);
   }

   private void initTable()
   {
      prev.setModel(new TableModel<List<String>>());
   }

   private void initLayout()
   {
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      header.add(custom);
      header.add(columnChooser);
      header.add(addColumn);
      header.add(removeColumn);
      header.add(export);
      header.add(Box.createRigidArea(new Dimension(30, 0)));
      header.add(info);

      add(header);
      add(prevScrollPane);

      StyleService
            .styleComponents(Arrays.stream(getComponents()).map(component -> ((JComponent) component)).toArray(JComponent[]::new));
   }

   public void addPrevColumn(String title, Function<List<String>, String> function)
   {
      getTableModel().addColumn(title, function);
   }

   public TableModel<List<String>> getTableModel()
   {
      //noinspection unchecked
      return (TableModel<List<String>>) prev.getModel();
   }

   public JButton getCustom()
   {
      return custom;
   }

   public JComboBox<Integer> getColumnChooser()
   {
      return columnChooser;
   }

   public JButton getAddColumn()
   {
      return addColumn;
   }

   public JButton getRemoveColumn()
   {
      return removeColumn;
   }

   public JButton getExport()
   {
      return export;
   }

   public JTable getPrev()
   {
      return prev;
   }

   public JButton getInfo()
   {
      return info;
   }
}
