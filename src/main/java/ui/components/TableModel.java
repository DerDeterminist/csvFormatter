package ui.components;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class TableModel<T> extends AbstractTableModel
{
   private List<T> rows = new ArrayList<>();
   private List<Function<T, ?>> columns = new ArrayList<>();
   private List<String> columnNames = new ArrayList<>();

   @Override

   public int getRowCount()
   {
      return rows.size();
   }

   @Override
   public int getColumnCount()
   {
      return columns.size();
   }

   @Override
   public Object getValueAt(int rowIndex, int columnIndex)
   {
      return columns.get(columnIndex).apply(rows.get(rowIndex));
   }

   @Override
   public Class<?> getColumnClass(int columnIndex)
   {
      Object value = columns.get(columnIndex).apply(rows.get(0));
      if (value != null)
      {
         return value.getClass();
      }
      return Object.class;
   }

   @Override
   public String getColumnName(int column)
   {
      return columnNames.get(column);
   }

   public List<T> getValues()
   {
      return rows;
   }

   public void addRows(Collection<T> collection)
   {
      rows.addAll(collection);
      fireTableRowsInserted(rows.size() - collection.size() - 1, rows.size() - 1);
   }

   public void addRow(T value)
   {
      rows.add(value);
      fireTableRowsInserted(rows.size() - 2, rows.size() - 1);
   }

   public void addColumn(String name, Function<T, ?> column)
   {
      columnNames.add(name);
      columns.add(column);
      fireTableStructureChanged();
   }

   public void removeColumn(int index)
   {
      columnNames.remove(index);
      columns.remove(index);
      fireTableStructureChanged();
   }

   public void removeRows(int... index)
   {
      for (int i = index.length - 1; i > -1; i--)
      {
         rows.remove(index[i]);
         fireTableRowsDeleted(index[i], index[i]);
      }
   }
}