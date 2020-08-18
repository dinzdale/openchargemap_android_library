package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CheckinStatusType(
    @SerializedName("ID")
    val iD: Int = 0,
    @SerializedName("IsAutomatedCheckin")
    val isAutomatedCheckin: Boolean = false,
    @SerializedName("IsPositive")
    val isPositive: Any? = null,
    @SerializedName("Title")
    val title: String = ""
)