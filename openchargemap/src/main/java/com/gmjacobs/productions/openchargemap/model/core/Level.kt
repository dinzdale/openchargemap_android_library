package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Level(
    @SerializedName("Comments")
    val comments: String,
    @SerializedName("ID")
    val iD: Int,
    @SerializedName("IsFastChargeCapable")
    val isFastChargeCapable: Boolean,
    @SerializedName("Title")
    val title: String
)