package ui;

import app.App;
import conts.PatternEntity;
import services.LoggingService;
import services.PersistenceService;

import javax.swing.WindowConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;

public class RootController
{
   private RootView view = new RootView();

   private FileController fileController = new FileController();
   private PrevController prevController = new PrevController();
   private PatternController patternController = new PatternController();

   private String matchesFileName = App.getPath() + "matches.json";

   public RootController()
   {
      view.setLeft(fileController.getView());
      view.setCenter(patternController.getView());
      view.setRight(prevController.getView());

      initFunctionalListeners();
      initData();

      view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      view.pack();
      view.setVisible(true);
      LoggingService.getLogger().log(Level.INFO, "UI init done");
   }


   private void initData()
   {
      PatternEntity entity = PersistenceService.load(PatternEntity.class, matchesFileName);
      if (entity != null)
      {
         LoggingService.getLogger()
               .log(Level.INFO, "loaded pattern from " + matchesFileName + System.lineSeparator() + entity.getPattern());
         patternController.setPatters(entity.getPattern());
      }
   }

   private void initFunctionalListeners()
   {
      patternController.addSelectionListener(pattern -> prevController.setPattern(pattern));
      fileController.addSelectionListener(csvFile -> prevController.setCsvFile(csvFile));

      view.addWindowListener(new WindowAdapter()
      {
         @Override
         public void windowClosing(WindowEvent windowEvent)
         {
            PatternEntity entity = new PatternEntity();
            entity.setPattern(patternController.getPatterns());

            PersistenceService.save(entity, matchesFileName);
            LoggingService.getLogger().log(Level.INFO,
                  "saved pattern to " + matchesFileName + System.lineSeparator() + entity.getPattern());
         }
      });
   }
}
