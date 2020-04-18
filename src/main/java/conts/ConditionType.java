package conts;

import com.google.gson.annotations.SerializedName;

public enum ConditionType
{
   @SerializedName("0")
   HOLDER,
   @SerializedName("1")
   NOT_NULL,
   @SerializedName("2")
   HAS_PREVIOUS,
   @SerializedName("3")
   HAS_NO_PREVIOUS,
}
