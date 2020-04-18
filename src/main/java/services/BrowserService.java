package services;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class BrowserService
{
   public static void openLink(String link)
   {
      if (isBrowsingSupported())
      {
         try
         {
            Desktop.getDesktop().browse(new URI(link));
         }
         catch (IOException | URISyntaxException e)
         {
            throw new RuntimeException(e);
         }
      }
   }

   private static boolean isBrowsingSupported()
   {
      return Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
   }
}
