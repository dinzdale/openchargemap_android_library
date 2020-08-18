package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SubmissionStatus(
    @SerializedName("ID")
    val iD: Int,
    @SerializedName("IsLive")
    val isLive: Boolean,
    @SerializedName("Title")
    val title: String
)