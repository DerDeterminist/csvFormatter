package ui;

import conts.Pattern;
import services.IconService;
import services.StyleService;
import ui.components.TableModel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.function.Function;

public class PatternView extends JPanel
{
   private JTextField name = new JTextField();

   private JButton add = new JButton(IconService.getImageIcon(IconService.IconType.ADD, 30, 30));
   private JButton remove = new JButton(IconService.getImageIcon(IconService.IconType.REMOVE, 30, 30));

   private JButton exportPattern = new JButton(IconService.getImageIcon(IconService.IconType.EXPORT, 30, 30));
   private JButton importPattern = new JButton(IconService.getImageIcon(IconService.IconType.IMPORT, 30, 30));

   private JPanel header = new JPanel();

   private JTable patterns = new JTable();
   private JScrollPane patternsScrollPane = new JScrollPane(patterns);

   public PatternView()
   {
      initComponents();
      initTable();
      initLayout();
   }

   private void initTable()
   {
      TableModel<Pattern> model = new TableModel<>();

      patterns.addColumn(new TableColumn());

      model.addColumn("name", (Function<Pattern, String>) Pattern::getName);

      patterns.setModel(model);
      patterns.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

   private void initLayout()
   {
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      header.add(name);
      header.add(add);
      header.add(remove);
      header.add(importPattern);
      header.add(exportPattern);

      add(header);
      add(patternsScrollPane);

      StyleService
            .styleComponents(Arrays.stream(getComponents()).map(component -> ((JComponent) component)).toArray(JComponent[]::new));
   }


   private void initComponents()
   {
      add.setPreferredSize(new Dimension(43, 40));
      remove.setPreferredSize(new Dimension(43, 40));
      importPattern.setPreferredSize(new Dimension(43, 40));
      exportPattern.setPreferredSize(new Dimension(43, 40));

      header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
      header.setMinimumSize(new Dimension(380, 60));

      name.setPreferredSize(new Dimension(170, Constants.COMPONENT_HEIGHT));

      patterns.setPreferredScrollableViewportSize(new Dimension(235, 200));
      patterns.setFillsViewportHeight(true);

      SwingUtilities.invokeLater(() -> {
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
      });
   }

   public TableModel<Pattern> getTableModel()
   {
      //noinspection unchecked
      return (TableModel<Pattern>) patterns.getModel();
   }

   public JTextField getNamee()
   {
      return name;
   }

   public JButton getAdd()
   {
      return add;
   }

   public JButton getRemove()
   {
      return remove;
   }

   public JTable getPatterns()
   {
      return patterns;
   }

   public JButton getExportPattern()
   {
      return exportPattern;
   }

   public JButton getImportPattern()
   {
      return importPattern;
   }
}
