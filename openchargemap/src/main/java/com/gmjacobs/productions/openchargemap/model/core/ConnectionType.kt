package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "connection_type")
data class ConnectionType(
    @SerializedName("FormalName")
    @ColumnInfo(name="formal_name")
    val formalName: String? = null,
    @PrimaryKey
    @ColumnInfo(name="id")
    @SerializedName("ID")
    val iD: Int = 0,
    @ColumnInfo(name="is_disconnected")
    @SerializedName("IsDiscontinued")
    val isDiscontinued: Boolean = false,
    @ColumnInfo(name="is_obsolete")
    @SerializedName("IsObsolete")
    val isObsolete: Boolean = false,
    @SerializedName("Title")
    val title: String = ""
)