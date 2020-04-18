package ui.sub;

import conts.Condition;
import conts.CsvFile;
import conts.Match;
import conts.MatchType;
import ui.components.RegExRowView;
import ui.components.RegexRowController;
import ui.regexRows.RegExRowAfterController;
import ui.regexRows.RegExRowBeforeController;
import ui.regexRows.RegExRowBetweenController;
import ui.regexRows.RegExRowColumnController;
import ui.regexRows.RegExRowFirstCharsController;
import ui.regexRows.RegExRowLastCharsController;
import ui.regexRows.RegExRowTextController;

import javax.swing.JDialog;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomRegExController
{
   private CustomRegExView view;
   private CsvFile csvFile;

   private Match rootMatch = new Match(MatchType.HOLDER);
   private boolean userWantsIt = false;

   private List<RegexRowController<? extends RegExRowView>> regExRowControllers = new ArrayList<>();

   public CustomRegExController(CsvFile csvFile, Frame owner, Consumer<Optional<Match>> matchConsumer)
   {
      this.csvFile = csvFile;

      regExRowControllers.add(new RegExRowColumnController(this::addSubMatch));
      regExRowControllers.add(new RegExRowTextController(this::addSubMatch));
      regExRowControllers.add(new RegExRowFirstCharsController(this::addSubMatch));
      regExRowControllers.add(new RegExRowLastCharsController(this::addSubMatch));
      regExRowControllers.add(new RegExRowBeforeController(this::addSubMatch));
      regExRowControllers.add(new RegExRowAfterController(this::addSubMatch));
      regExRowControllers.add(new RegExRowBetweenController(this::addSubMatch));

      this.view = new CustomRegExView(
            regExRowControllers.stream().map(RegexRowController::getView).collect(Collectors.toList()),
            owner,
            windowEvent -> {
               matchConsumer.accept(getMatch());
               view.dispose();
            });

      initData();
      initFunctionalListeners();

      view.pack();
      view.setVisible(true);
   }

   private void initData()
   {
      List<Integer> rows =
            IntStream.range(0, csvFile.getColumns().get(0).size()).boxed().map(integer -> integer + 1).collect(Collectors.toList());
      regExRowControllers.forEach(row -> row.initData(rows));
      view.getTableModel().addRows(csvFile.getColumns());
   }

   private void initFunctionalListeners()
   {
      view.getCancel().addActionListener(actionEvent -> {
         rootMatch = null;
         view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
         view.dispose();
      });
      view.getOk().addActionListener(actionEvent -> {
         userWantsIt = true;
         view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
         view.dispose();
      });
   }

   private void addSubMatch(Match match, Condition condition)
   {
      if (condition != null)
      {
         match.setCondition(condition);
      }
      rootMatch.getSubMatches().add(match);
      updateTable();
   }

   private void updateTable()
   {
      if (view.getTableModel().getColumnCount() == 0)
      {
         view.getTableModel().addColumn("custom", rootMatch.getFunction());
      }
      else
      {
         view.getPrev().repaint();
      }
   }

   private Optional<Match> getMatch()
   {
      if (userWantsIt)
      {
         return Optional.ofNullable(rootMatch);
      }
      else
      {
         return Optional.empty();
      }
   }

   public JDialog getView()
   {
      return view;
   }
}
