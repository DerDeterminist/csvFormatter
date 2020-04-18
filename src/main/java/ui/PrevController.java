package ui;

import conts.CsvFile;
import conts.Match;
import conts.OutputConfig;
import conts.Pattern;
import services.CsvService;
import services.LoggingService;
import ui.components.TableModel;
import ui.sub.CustomRegExController;
import ui.sub.InfoController;
import ui.sub.OutputController;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import static conts.MatchType.COLUMN;

public class PrevController
{
   private PrevView view = new PrevView();
   private CsvFile csvFile;
   private Pattern pattern;

   public PrevController()
   {
      updateUi();
      initFunctionalListeners();
   }

   private void initFunctionalListeners()
   {
      view.getCustom().addActionListener(actionEvent -> {

         if (csvFile != null && pattern != null)
         {
            new CustomRegExController(
                  csvFile,
                  (Frame) SwingUtilities.getRoot(getView()),
                  optionalMatch -> optionalMatch.ifPresent(match -> {
                     pattern.getMatches().add(match);

                     view.addPrevColumn("custom", match.getFunction());
                  }));
         }
      });

      view.getAddColumn().addActionListener(actionEvent ->
      {
         if (csvFile != null && pattern != null)
         {
            Integer selectedItem = (Integer) view.getColumnChooser().getSelectedItem();
            if (selectedItem != null)
            {
               Match match = new Match(COLUMN);
               match.setColumn(selectedItem);

               pattern.getMatches().add(match);

               view.addPrevColumn(selectedItem.toString(), match.getFunction());
            }
         }
      });

      view.getRemoveColumn().addActionListener(actionEvent -> {
         if (csvFile != null && pattern != null)
         {
            int selectedColumn = view.getPrev().getSelectedColumn();
            if (selectedColumn != -1)
            {
               pattern.getMatches().remove(selectedColumn);
               view.getTableModel().removeColumn(selectedColumn);
            }
         }
      });

      view.getExport().addActionListener(actionEvent ->
            new OutputController((Frame) SwingUtilities.getRoot(
                  getView()),
                  outputConfigOptional -> outputConfigOptional.ifPresent(this::output)));

      view.getInfo().addActionListener(actionEvent -> new InfoController((Frame) SwingUtilities.getRoot(getView())));
   }

   private void output(OutputConfig config)
   {
      List<List<String>> rows = new ArrayList<>();

      for (int rowIndex = 0; rowIndex < view.getTableModel().getRowCount(); rowIndex++)
      {
         List<String> column = new ArrayList<>();
         for (int columnIndex = 0; columnIndex < view.getTableModel().getColumnCount(); columnIndex++)
         {
            column.add((String) view.getTableModel().getValueAt(rowIndex, columnIndex));
         }
         rows.add(column);
      }
      if (!rows.isEmpty())
      {
         getFile().ifPresent(file -> {
            CsvService.write(file, rows, config);
            LoggingService.getLogger()
                  .log(Level.INFO, "exported csv to " + file.getAbsolutePath() + "content:" + System.lineSeparator() + rows);
         });
      }
   }

   private Optional<File> getFile()
   {
      JFileChooser fileChooser = new JFileChooser();
      FileNameExtensionFilter csv = new FileNameExtensionFilter("csv", "csv");
      fileChooser.addChoosableFileFilter(csv);
      fileChooser.setAcceptAllFileFilterUsed(false);
      fileChooser.setMultiSelectionEnabled(false);
      fileChooser.showSaveDialog(view);

      File file = fileChooser.getSelectedFile();

      if (file == null)
      {
         return Optional.empty();
      }

      if (file.getName().endsWith(".csv"))
      {
         return Optional.of(file);
      }
      return Optional.of(new File(file.getAbsolutePath() + ".csv"));
   }

   public Component getView()
   {
      return view;
   }

   public void setPattern(Pattern pattern)
   {
      this.pattern = pattern;
      updateUi();
   }

   public void setCsvFile(CsvFile csvFile)
   {
      this.csvFile = csvFile;
      updateUi();
   }

   private void updateUi()
   {
      if (csvFile != null && pattern != null)
      {
         TableModel<List<String>> tableModel = new TableModel<>();
         tableModel.addRows(csvFile.getColumns());
         view.getPrev().setModel(tableModel);

         DefaultComboBoxModel<Integer> comboBoxModel = new DefaultComboBoxModel<>();
         for (int i = 0; i < csvFile.getColumns().get(0).size(); i++)
         {
            comboBoxModel.addElement(i + 1);
         }
         view.getColumnChooser().setModel(comboBoxModel);

         for (Match match : pattern.getMatches())
         {
            switch (match.getType())
            {
               case COLUMN:
                  view.addPrevColumn(match.getColumn().toString(), match.getFunction());
                  break;
               case PLAN_TEXT:
               case HOLDER:
                  view.addPrevColumn("custom", match.getFunction());
                  break;
            }
         }
      }
   }
}
