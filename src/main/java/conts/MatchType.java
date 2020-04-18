package conts;

import com.google.gson.annotations.SerializedName;

public enum MatchType
{
   @SerializedName("0")
   COLUMN,
   @SerializedName("1")
   PLAN_TEXT,
   @SerializedName("2")
   HOLDER,
   @SerializedName("3")
   LAST_CHARS,
   @SerializedName("3")
   FIST_CHARS,
   @SerializedName("4")
   BEFORE_TEXT,
   @SerializedName("5")
   AFTER_TEXT,
   @SerializedName("6")
   BETWEEN_TEXT
}
