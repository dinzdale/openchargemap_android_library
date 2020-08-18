package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "charger_type")
data class ChargerType(
    @SerializedName("Comments")
    @ColumnInfo(name="comments")
    val comments: String = "",
    @SerializedName("ID")
    @ColumnInfo(name="id")
    @PrimaryKey
    val iD: Int = 0,
    @SerializedName("IsFastChargeCapable")
    @ColumnInfo(name="fast_charge_capable")
    val isFastChargeCapable: Boolean = false,
    @SerializedName("Title")
    @ColumnInfo(name="title")

    val title: String = ""
)