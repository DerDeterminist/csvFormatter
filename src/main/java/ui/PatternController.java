package ui;

import conts.Pattern;
import conts.PatternEntity;
import services.LoggingService;
import services.PersistenceService;

import javax.swing.JFileChooser;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;

import static java.awt.event.KeyEvent.VK_ENTER;

public class PatternController
{
   private PatternView view = new PatternView();

   public PatternController()
   {
      initFunctionalListeners();
      initHandlingListeners();
   }

   private void initHandlingListeners()
   {
      view.getNamee().addKeyListener(new KeyAdapter()
      {
         @Override
         public void keyPressed(KeyEvent keyEvent)
         {
            if (keyEvent.getKeyCode() == VK_ENTER)
            {
               addPattern();
            }
         }
      });
   }

   private void initFunctionalListeners()
   {
      view.getAdd().addActionListener(actionEvent -> addPattern());

      view.getRemove().addActionListener(actionEvent -> {
         int selectedRow = view.getPatterns().getSelectedRow();
         if (selectedRow != -1)
         {
            view.getTableModel().removeRows(selectedRow);
         }
      });
      view.getExportPattern().addActionListener(actionEvent ->

            getFile(false).ifPresent(file -> {
               PatternEntity entity = new PatternEntity();
               entity.setPattern(view.getTableModel().getValues());
               PersistenceService.save(entity, file.getAbsolutePath());
               LoggingService.getLogger().log(Level.INFO,
                     "exported pattern to " + file.getAbsolutePath() + System.lineSeparator() + entity.getPattern());
            }));
      view.getImportPattern().addActionListener(actionEvent ->

            getFile(true).ifPresent(file -> {
               PatternEntity entity = PersistenceService.load(PatternEntity.class, file.getAbsolutePath());
               if (entity != null)
               {
                  LoggingService.getLogger().log(Level.INFO,
                        "imported pattern from " + file.getAbsolutePath() + System.lineSeparator() + entity.getPattern());
                  view.getTableModel().addRows(entity.getPattern());
               }
            }));
   }

   private void addPattern()
   {
      String name = view.getNamee().getText();
      if (name != null && !name.isEmpty())
      {
         Pattern pattern = new Pattern();
         pattern.setName(name);
         view.getTableModel().addRow(pattern);
         view.getNamee().setText("");
         view.getPatterns().setRowSelectionInterval(
               view.getTableModel().getRowCount() - 1,
               view.getTableModel().getRowCount() - 1);
      }
   }

   private Optional<File> getFile(boolean open)
   {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setMultiSelectionEnabled(false);
      if (open)
      {
         fileChooser.showOpenDialog(view);
      }
      else
      {
         fileChooser.showSaveDialog(view);
      }
      return Optional.ofNullable(fileChooser.getSelectedFile());
   }

   public Component getView()
   {
      return view;
   }

   public void setPatters(List<Pattern> patters)
   {
      view.getTableModel().addRows(patters);
   }

   public List<Pattern> getPatterns()
   {
      return view.getTableModel().getValues();
   }

   public void addSelectionListener(Consumer<Pattern> listener)
   {
      view.getPatterns().getSelectionModel().addListSelectionListener(listSelectionEvent -> {
         int selectedRow = view.getPatterns().getSelectedRow();
         if (selectedRow != -1 && selectedRow < view.getTableModel().getValues().size())
         {
            listener.accept(view.getTableModel().getValues().get(selectedRow));
         }
      });
   }
}
