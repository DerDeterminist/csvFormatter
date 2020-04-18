package services;

import ui.components.JLabelLink;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.JTextComponent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StyleService
{
   private static Color textBackground = new Color(43, 43, 43);
   private static Color panelBackground = new Color(0x3C3F41);
   private static Color textColor = new Color(219, 219, 219);
   private static Color linkTextColor = new Color(4, 133, 203);
   private static Color tableSelectedItemColor = new Color(0x0BA2C0);

   private static Font font = new JLabel().getFont().deriveFont(Font.BOLD, 16);

   public static void styleComponents(Component... components)
   {
      for (Component component : components)
      {
         if (component instanceof JComponent)
         {
            styleComponent(((JComponent) component));
         }
         if (component instanceof Container)
         {
            styleComponents(((Container) component).getComponents());
         }
      }
   }

   private static void styleComponent(JComponent jComponent)
   {
      if (!(jComponent instanceof JViewport))
      {
         jComponent.setBorder(new LineBorder(panelBackground));
      }

      if (jComponent instanceof JSplitPane)
      {
         styleSplitPane(jComponent);
      }
      if (jComponent instanceof JTable)
      {
         styleJTable((JTable) jComponent);
      }
      if (jComponent instanceof JTextComponent)
      {
         styleJTextComponent(((JTextComponent) jComponent));
      }

      if (jComponent instanceof JSplitPane || jComponent instanceof JPanel)
      {
         stylePanel(jComponent);
      }
      else
      {
         jComponent.setBackground(textBackground);
      }

      if (jComponent instanceof JLabelLink)
      {
         styleJLinkLabel(((JLabelLink) jComponent));
      }
      else
      {
         jComponent.setForeground(textColor);
         jComponent.setFont(font);
      }
   }

   private static void styleJLinkLabel(JLabelLink jComponent)
   {
      jComponent.setForeground(linkTextColor);
      Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
      attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
      jComponent.setFont(StyleService.font.deriveFont(attributes));
   }

   private static void styleJTextComponent(JTextComponent jComponent)
   {
      jComponent.setCaretColor(textColor);
   }

   private static void stylePanel(JComponent jComponent)
   {
      jComponent.setBackground(panelBackground);
      jComponent.setBorder(new LineBorder(panelBackground));
   }

   private static void styleSplitPane(JComponent jComponent)
   {
      ((JSplitPane) jComponent).setDividerSize(5);
      jComponent.setBorder(new LineBorder(panelBackground));
      Arrays.stream(jComponent.getComponents()).filter(component -> component instanceof BasicSplitPaneDivider).findAny()
            .ifPresent(component -> ((BasicSplitPaneDivider) component).setBorder(new LineBorder(panelBackground, 4)));
   }

   private static void styleJTable(JTable jTable)
   {
      jTable.getTableHeader().setReorderingAllowed(false);

      jTable.setSelectionBackground(StyleService.tableSelectedItemColor);
      jTable.setSelectionForeground(StyleService.textColor);
      jTable.setRowHeight(24);
      jTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
      {
         @Override
         public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1)
         {
            if (o == null)
            {
               o = "null";
            }
            return super.getTableCellRendererComponent(jTable, o, b, b1, i, i1);
         }
      });
      jTable.setDefaultRenderer(String.class, new DefaultTableCellRenderer()
      {
         @Override
         public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1)
         {
            if (o == null)
            {
               o = "null";
            }
            return super.getTableCellRendererComponent(jTable, o, b, b1, i, i1);
         }
      });
      jTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer()
      {
         @Override
         public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1)
         {
            Component component = super.getTableCellRendererComponent(jTable, o, b, b1, i, i1);
            if (i == -1) // header
            {
               component.setBackground(panelBackground);
            }
            return component;
         }
      });
   }
}
