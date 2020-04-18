package ui.sub;

import conts.Condition;
import conts.ConditionType;
import ui.components.ConditionRowController;
import ui.components.ConditionRowView;
import ui.condtionRows.ConditionHasNoPreviousController;
import ui.condtionRows.ConditionHasPreviousController;
import ui.condtionRows.ConditionNotNullController;

import javax.swing.JDialog;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConditionController
{
   private ConditionView view;

   private boolean userWantsIt = false;

   private List<ConditionRowController<? extends ConditionRowView>> conditionRowControllers = new ArrayList<>();


   public ConditionController(Consumer<Optional<Condition>> windowEventConsumer)
   {
      conditionRowControllers.add(new ConditionNotNullController());
      conditionRowControllers.add(new ConditionHasPreviousController());
      conditionRowControllers.add(new ConditionHasNoPreviousController());

      this.view = new ConditionView(
            conditionRowControllers
                  .stream()
                  .map(ConditionRowController::getView)
                  .collect(Collectors.toList()),
            windowEvent -> {
               windowEventConsumer.accept(getCondition());
               view.dispose();
            });

      initFunctionalListeners();

      view.pack();
      view.setVisible(true);
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

   private Optional<Condition> getCondition()
   {
      if (userWantsIt)
      {
         boolean hasCondition = conditionRowControllers.stream().anyMatch(controller -> controller.getCondition().isPresent());
         if (hasCondition)
         {
            Condition rootCondition = new Condition(ConditionType.HOLDER);
            conditionRowControllers.forEach(
                  controller -> controller.getCondition().ifPresent(condition -> rootCondition.getSubConditions().add(condition)));
            return Optional.of(rootCondition);
         }
      }
      return Optional.empty();
   }

   public JDialog getView()
   {
      return view;
   }
}
