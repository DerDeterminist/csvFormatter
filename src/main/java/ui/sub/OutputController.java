package ui.sub;

import conts.OutputConfig;

import javax.swing.DefaultComboBoxModel;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.util.Optional;
import java.util.function.Consumer;

public class OutputController
{
   private OutputView view;

   private boolean userWantsIt = false;

   public OutputController(Frame owner, Consumer<Optional<OutputConfig>> outputConfigConsumer)
   {
      view = new OutputView(owner, windowEvent -> {
         outputConfigConsumer.accept(getOutputConfig());
         view.dispose();
      });
      initFunctionalListeners();
      initData();

      view.pack();
      view.setVisible(true);
   }

   private void initData()
   {
      DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) view.getSeparator().getModel();
      model.addElement(",");
      model.addElement(";");
   }

   private void initFunctionalListeners()
   {
      view.getCancel().addActionListener(actionEvent -> {
         view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
         view.dispose();
      });
      view.getOk().addActionListener(actionEvent -> {
         userWantsIt = true;
         view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
         view.dispose();
      });
   }

   public Optional<OutputConfig> getOutputConfig()
   {
      if (userWantsIt)
      {
         OutputConfig config = new OutputConfig();
         config.setSeparator((String) view.getSeparator().getSelectedItem());
         return Optional.of(config);
      }
      return Optional.empty();
   }

   public OutputView getView()
   {
      return view;
   }
}
