package ui;

import conts.CsvFile;
import conts.InputConfig;
import services.CsvService;
import services.LoggingService;
import ui.components.TableModel;
import ui.sub.InputController;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.awt.Frame;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;

public class FileController
{
   private FileView view = new FileView();
   private Consumer<CsvFile> csvChangeListener;


   public FileController()
   {
      initFunctionalListeners();
   }

   private void initFunctionalListeners()
   {
      view.getAdd().addActionListener(actionEvent ->
            new InputController((Frame) SwingUtilities.getRoot(
                  getView()),
                  optionalInputConfig -> optionalInputConfig.ifPresent(this::setData)));
   }

   private void setData(InputConfig config)
   {
      getFile().ifPresent(file -> {
         view.getFileName().setText(file.getName());

         CsvFile csvFile = new CsvFile();
         List<List<String>> csv = CsvService.read(file, config);
         LoggingService.getLogger().log(Level.INFO, "loaded CSV: " + System.lineSeparator() + csv.toString());
         csvFile.setColumns(csv);

         TableModel<List<String>> tableModel = new TableModel<>();

         for (int i = 0; i < csv.get(0).size(); i++)
         {
            int finalI = i;
            tableModel.addColumn(String.valueOf(i + 1), strings -> {
               if (strings.size() > finalI)
               {
                  return strings.get(finalI);
               }
               return "";
            });
         }

         tableModel.addRows(csv);
         view.getInputPrev().setModel(tableModel);

         csvChangeListener.accept(csvFile);
      });
   }


   public void addSelectionListener(Consumer<CsvFile> csvChangelistener)
   {

      this.csvChangeListener = csvChangelistener;
   }

   private Optional<File> getFile()
   {
      JFileChooser fileChooser = new JFileChooser();
      FileNameExtensionFilter csv = new FileNameExtensionFilter("csv", "csv");
      fileChooser.addChoosableFileFilter(csv);
      fileChooser.setAcceptAllFileFilterUsed(false);
      fileChooser.setMultiSelectionEnabled(false);
      fileChooser.showOpenDialog(view);
      return Optional.ofNullable(fileChooser.getSelectedFile());
   }

   public Component getView()
   {
      return view;
   }
}
