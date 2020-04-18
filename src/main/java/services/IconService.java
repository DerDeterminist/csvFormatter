package services;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


public class IconService
{
   private static HashMap<IconType, ImageIcon> imageCache = new HashMap<>();

   public static ImageIcon getImageIcon(IconType icon, int with, int height)
   {
      return imageCache.computeIfAbsent(icon, iconType -> loadIcon(iconType, with, height));
   }

   private static ImageIcon loadIcon(IconType icon, int with, int height)
   {
      try
      {
         InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(icon.path);
         @SuppressWarnings("ConstantConditions") BufferedImage image = ImageIO.read(input);
         Image scaledImage = new ImageIcon(image).getImage();
         Image newImg = scaledImage.getScaledInstance(with, height, java.awt.Image.SCALE_SMOOTH);
         return new ImageIcon(newImg);
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
   }

   public enum IconType
   {
      ADD("add.png"),
      REMOVE("remove.png"),
      IMPORT("import.png"),
      EXPORT("export.png"),
      CUSTOM("custom.png"),
      NO("no.png"),
      YES("yes.png"),
      LOGO("logo.png"),
      INFO("info.png"),
      WHEN("flash.png"),
      FOLDER("folder.png");

      private String path;

      IconType(String path)
      {
         this.path = path;
      }
   }
}

