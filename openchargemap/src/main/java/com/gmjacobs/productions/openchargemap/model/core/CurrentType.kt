package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "current_type")
data class CurrentType(
    @SerializedName("Description")
    @ColumnInfo(name = "description")
    val description: String = "",
    @SerializedName("ID")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val iD: Int = 0,
    @SerializedName("Title")
    @ColumnInfo(name = "title")
    val title: String = ""
)