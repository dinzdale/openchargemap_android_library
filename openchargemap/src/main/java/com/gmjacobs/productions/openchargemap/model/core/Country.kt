package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "country")
data class Country(
    @SerializedName("ContinentCode")
    @ColumnInfo(name="continent_code")
    val continentCode: String? = null,
    @SerializedName("ID")
    @ColumnInfo(name="id")
    @PrimaryKey
    val iD: Int = 0,
    @SerializedName("ISOCode")
    @ColumnInfo(name="iso_country_code")
    val iSOCode: String = "",
    @ColumnInfo(name="title")
    @SerializedName("Title")
    val title: String = ""
)