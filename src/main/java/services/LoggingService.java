package services;

import app.App;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingService
{
   private static String logPath = App.getPath() + "log\\";
   private static Logger logger;
   private static int old = 14;

   public static Logger getLogger()
   {
      if (logger == null)
      {
         initLogger();
      }
      return logger;
   }

   private static void initLogger()
   {
      createDir();
      addFileHandler();
      setUncaughtHandler();
      setAWTEventQueueLoggerProxy();
      removeOldLogs();
   }

   private static void createDir()
   {
      //noinspection ResultOfMethodCallIgnored
      new File(logPath).mkdirs();
   }

   private static void addFileHandler()
   {
      try
      {
         FileHandler fileHandler =
               new FileHandler(logPath + "log" + new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss").format(new Date()) + ".xml");
         logger = Logger.getGlobal();
         logger.addHandler(fileHandler);
      }
      catch (IOException e)
      {
         System.out.println("fucked: " + System.lineSeparator() + e);
      }
   }

   private static void setUncaughtHandler()
   {
      Thread.currentThread().setUncaughtExceptionHandler(
            (thread, throwable) -> logThrowable(throwable));
      logger.log(Level.INFO, "Logger init done");
   }

   private static void setAWTEventQueueLoggerProxy()
   {
      Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueue()
      {
         @Override
         protected void dispatchEvent(AWTEvent awtEvent)
         {
            try
            {
               super.dispatchEvent(awtEvent);
            }
            catch (Exception e)
            {
               logThrowable(e);
            }
         }
      });
   }

   private static void removeOldLogs()
   {
      try
      {
         Files.walk(new File(logPath).toPath())
               .filter(path -> path.toFile().isFile())
               .filter(path -> {
                  try
                  {
                     BasicFileAttributes attributes =
                           Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes();
                     Instant start = attributes.creationTime().toInstant();

                     long dayDiff = ChronoUnit.DAYS.between(start, Instant.now());
                     return dayDiff > old;
                  }
                  catch (IOException e)
                  {
                     logThrowable(e);
                  }
                  return false;
               })
               .forEach(path -> path.toFile().deleteOnExit());
      }
      catch (IOException e)
      {
         logThrowable(e);
      }
   }

   private static void logThrowable(Throwable e)
   {
      logger.log(Level.INFO, "", e);
   }
}
