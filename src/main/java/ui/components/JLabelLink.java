package ui.components;

import services.BrowserService;

import javax.swing.JLabel;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.Cursor.HAND_CURSOR;

public class JLabelLink extends JLabel
{

   private String link;

   public JLabelLink(String text, String link)
   {
      this.link = link;
      setText(text);

      style();
   }

   private void style()
   {
      setCursor(new Cursor(HAND_CURSOR));
      addMouseListener(new MouseAdapter()
      {
         @Override
         public void mouseClicked(MouseEvent mouseEvent)
         {
            BrowserService.openLink(link);
         }
      });
   }

   public String getLink()
   {
      return link;
   }
}