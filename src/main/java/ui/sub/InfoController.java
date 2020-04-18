package ui.sub;

import app.App;

import java.awt.Desktop;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;

public class InfoController
{
   private InfoView view;

   public InfoController(Frame owner)
   {
      view = new InfoView(owner);
      initFunctionalListeners();

      view.pack();
      view.setVisible(true);
   }

   private void initFunctionalListeners()
   {
      view.getHelp().addActionListener(actionEvent -> {
         try
         {
            Desktop.getDesktop().open(App.getUserDoc());
         }
         catch (IOException e)
         {
            throw new RuntimeException(e);
         }
      });
      view.getLogs().addActionListener(actionEvent -> {
         try
         {
            Desktop.getDesktop().open(new File(App.getPath()));
         }
         catch (IOException e)
         {
            throw new RuntimeException(e);
         }
      });
   }

   public InfoView getView()
   {
      return view;
   }
}
