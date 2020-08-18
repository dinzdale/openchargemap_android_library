package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UserCommentType(
    @SerializedName("ID")
    val iD: Int = 0,
    @SerializedName("Title")
    val title: String = ""
)