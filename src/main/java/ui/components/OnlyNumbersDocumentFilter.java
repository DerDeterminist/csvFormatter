package ui.components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class OnlyNumbersDocumentFilter extends DocumentFilter
{

   private boolean allDigit(String text)
   {
      return text.chars().allMatch(Character::isDigit);
   }

   @Override
   public void insertString(FilterBypass fb, int offset, String string,
                            AttributeSet attr) throws BadLocationException
   {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.insert(offset, string);

      if (allDigit(sb.toString()))
      {
         super.insertString(fb, offset, string, attr);
      }
   }

   @Override
   public void replace(FilterBypass fb, int offset, int length, String text,
                       AttributeSet attrs) throws BadLocationException
   {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.replace(offset, offset + length, text);

      if (allDigit(sb.toString()))
      {
         super.replace(fb, offset, length, text, attrs);
      }
   }

   @Override
   public void remove(FilterBypass fb, int offset, int length)
         throws BadLocationException
   {
      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.delete(offset, offset + length);

      if (allDigit(sb.toString()))
      {
         super.remove(fb, offset, length);
      }
   }
}