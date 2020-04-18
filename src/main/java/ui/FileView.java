package ui;

import services.IconService;
import services.StyleService;
import ui.components.TableModel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;

public class FileView extends JPanel
{
   private JLabel fileName = new JLabel();

   private JButton add = new JButton(IconService.getImageIcon(IconService.IconType.ADD, 30, 30));

   private JPanel header = new JPanel();

   private JTable inputPrev = new JTable();
   private JScrollPane filesScrollPane = new JScrollPane(inputPrev);

   public FileView()
   {
      initComponents();
      initTable();
      initLayout();
   }

   private void initTable()
   {
      TableModel<List<String>> model = new TableModel<>();

      inputPrev.setModel(model);
      inputPrev.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

   private void initComponents()
   {
      add.setPreferredSize(new Dimension(43, 40));

      fileName.setPreferredSize(new Dimension(260, 28));

      header.setMaximumSize(new Dimension(370, 60));
      header.setMinimumSize(new Dimension(370, 60));

      inputPrev.setPreferredScrollableViewportSize(new Dimension(235, 200));
      inputPrev.setFillsViewportHeight(true);
   }

   private void initLayout()
   {
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      header.add(add);
      header.add(fileName);
      add(header);
      add(filesScrollPane);

      StyleService
            .styleComponents(Arrays.stream(getComponents()).map(component -> ((JComponent) component)).toArray(JComponent[]::new));
   }

   public JTable getInputPrev()
   {
      return inputPrev;
   }

   public JButton getAdd()
   {
      return add;
   }

   public JLabel getFileName()
   {
      return fileName;
   }
}
