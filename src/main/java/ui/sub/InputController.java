package ui.sub;

import conts.InputConfig;

import javax.swing.DefaultComboBoxModel;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.util.Optional;
import java.util.function.Consumer;

public class InputController
{
   private InputView view;

   private boolean userWantsIt = false;

   public InputController(Frame owner, Consumer<Optional<InputConfig>> inputConfigConsumer)
   {
      view = new InputView(owner, windowEvent -> {
         inputConfigConsumer.accept(getInputConfig());
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
      view.getCancel().addActionListener(actionEvent -> view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING)));
      view.getOk().addActionListener(actionEvent -> {
         userWantsIt = true;
         view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
      });
   }

   private Optional<InputConfig> getInputConfig()
   {
      if (userWantsIt)
      {
         InputConfig config = new InputConfig();
         config.setSeparator(((String) view.getSeparator().getSelectedItem()));
         config.setRemoveQuotes(view.getRemoveQuotes().isSelected());
         config.setRemoveSingleQuotes(view.getRemoveSingleQuotes().isSelected());
         return Optional.of(config);
      }
      return Optional.empty();
   }

   public InputView getView()
   {
      return view;
   }
}
