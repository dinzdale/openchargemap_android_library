package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Connection(
    @SerializedName("Amps")
    val amps: Int? = null,
    @SerializedName("Comments")
    val comments: String? = null,
    @SerializedName("ConnectionType")
    val connectionType: ConnectionType? = null,
    @SerializedName("ConnectionTypeID")
    val connectionTypeID: Int? = null,
    @SerializedName("CurrentType")
    val currentType: CurrentType? = null,
    @SerializedName("CurrentTypeID")
    val currentTypeID: Int? = null,
    @SerializedName("ID")
    val iD: Int = 0,
    @SerializedName("Level")
    val level: Level? = null,
    @SerializedName("LevelID")
    val levelID: Int? = null,
    @SerializedName("PowerKW")
    val powerKW: Double? = null,
    @SerializedName("Quantity")
    val quantity: Int? = null,
    @SerializedName("Reference")
    val reference: String? = null,
    @SerializedName("StatusType")
    val statusType: StatusType? = null,
    @SerializedName("StatusTypeID")
    val statusTypeID: Int? = null,
    @SerializedName("Voltage")
    val voltage: Int? = null
)