package app;

import services.LoggingService;
import ui.RootController;

import javax.swing.SwingUtilities;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class App
{
   private static final String path = System.getProperty("user.home") + "\\csvFormatter\\";
   private static final File userDoc = new File("src\\main\\resources\\doc.pdf");


   public static void main(String[] args)
   {
      if (createDir())
      {
         openUserDoc();
      }
      LoggingService.getLogger().log(Level.INFO, "starting");

      SwingUtilities.invokeLater(RootController::new);
   }

   private static void openUserDoc()
   {
      try
      {
         Desktop.getDesktop().open(App.getUserDoc());
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
   }

   private static boolean createDir()
   {
      return new File(path).mkdirs();
   }

   public static String getPath()
   {
      return path;
   }

   public static File getUserDoc()
   {
      return userDoc;
   }
}