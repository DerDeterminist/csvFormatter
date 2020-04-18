package conts;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Match
{
   private Condition condition;

   private Integer column;

   private String text;
   private String text1;
   private MatchType type;

   private Integer chars;

   private List<Match> subMatches = new ArrayList<>();

   public Match(MatchType type)
   {
      this.type = type;
   }

   public Function<List<String>, String> getFunction()
   {
      return getFunction(null);
   }

   private Function<List<String>, String> getFunction(String previousText)
   {
      return values -> {

         String result = null;
         switch (type)
         {
            case HOLDER:
               if (!subMatches.isEmpty())
               {
                  for (Match subMatch : subMatches)
                  {
                     String apply = subMatch.getFunction(result).apply(values);
                     if (result == null)
                     {
                        result = apply;
                     }
                     else
                     {
                        result += apply;
                     }
                  }
               }
               break;
            case COLUMN:
               if (values.size() >= column)
               {
                  result = getValue(values);
               }
               break;
            case PLAN_TEXT:
               result = text;
               break;
            case FIST_CHARS:
               if (column != null && values.size() >= column && chars != null && chars >= 0)
               {
                  String value = getValue(values);
                  if (value.length() >= chars)
                  {
                     result = value.substring(0, chars);
                  }
               }
               break;
            case LAST_CHARS:
               if (column != null && values.size() >= column && chars != null && chars >= 0)
               {
                  String value = getValue(values);
                  if (value.length() > chars)
                  {
                     result = value.substring(value.length() - chars);
                  }
               }
               break;
            case AFTER_TEXT:
               if (column != null && text != null && !text.isEmpty())
               {
                  String value = getValue(values);
                  int index = value.indexOf(text);
                  if (index != -1)
                  {
                     result = value.substring(index + text.length());
                     result = clearEmptyString(result);
                  }
               }
               break;
            case BEFORE_TEXT:
               if (column != null && text != null && !text.isEmpty())
               {
                  String value = getValue(values);
                  int index = value.indexOf(text);
                  if (index != -1)
                  {
                     result = value.substring(0, index);
                     result = clearEmptyString(result);
                  }
               }
               break;
            case BETWEEN_TEXT:
               if (column != null && text != null && !text.isEmpty() && text1 != null && !text1.isEmpty())
               {
                  String value = getValue(values);
                  int index1 = value.indexOf(text);
                  int index2 = value.lastIndexOf(text1);
                  if (index1 != -1 && index2 != -1)
                  {
                     result = value.substring(index1 + text.length(), index2);
                     result = clearEmptyString(result);
                  }
               }
               break;
            default:
               throw new RuntimeException("not implement");
         }
         return condition == null ? result : condition.isValid(result, previousText) ? result : "";
      };
   }

   private String clearEmptyString(String result)
   {
      if ("".equals(result.trim()))
      {
         result = null;
      }
      return result;
   }

   private String getValue(List<String> strings)
   {
      return strings.get(column - 1);
   }

   public void setChars(Integer chars)
   {
      this.chars = chars;
   }

   public List<Match> getSubMatches()
   {
      return subMatches;
   }

   public Integer getColumn()
   {
      return column;
   }

   public void setColumn(Integer column)
   {
      this.column = column;
   }

   public void setText(String text)
   {
      this.text = text;
   }

   public MatchType getType()
   {
      return type;
   }

   public void setText1(String text1)
   {
      this.text1 = text1;
   }

   public Condition getCondition()
   {
      return condition;
   }

   public void setCondition(Condition condition)
   {
      this.condition = condition;
   }

   @Override
   public String toString()
   {
      return "Match{" +
            "condition=" + condition +
            ", column=" + column +
            ", text='" + text + '\'' +
            ", text1='" + text1 + '\'' +
            ", type=" + type +
            ", chars=" + chars +
            ", subMatches=" + subMatches +
            '}';
   }
}
