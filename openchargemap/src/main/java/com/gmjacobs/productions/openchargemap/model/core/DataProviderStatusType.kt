package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
data class DataProviderStatusType(
    @SerializedName("ID")
    var iD: Int = -1,
    @SerializedName("IsProviderEnabled")
    var isProviderEnabled: Boolean = false,
    @SerializedName("Title")
    var title: String = ""
)