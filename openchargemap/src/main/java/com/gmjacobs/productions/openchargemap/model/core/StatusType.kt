package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "status_type")
data class StatusType(
    @SerializedName("ID")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val iD: Int = 0,
    @SerializedName("IsOperational")
    @ColumnInfo(name = "operational")
    val isOperational: Boolean? = null,
    @SerializedName("IsUserSelectable")
    @ColumnInfo(name = "user_selectable")
    val isUserSelectable: Boolean = false,
    @SerializedName("Title")
    @ColumnInfo(name = "title")
    val title: String? = null
)