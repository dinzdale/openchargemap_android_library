package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.PropertyKey

@Keep
@Entity(tableName = "usage_type")
data class UsageType(
    @SerializedName("ID")
    @PrimaryKey
    @ColumnInfo(name="id")
    val iD: Int = 0,
    @SerializedName("IsAccessKeyRequired")
    @ColumnInfo(name="access_required")
    val isAccessKeyRequired: Boolean? = null,
    @SerializedName("IsMembershipRequired")
    @ColumnInfo(name="membership_required")
    val isMembershipRequired: Boolean? = null,
    @SerializedName("IsPayAtLocation")
    @ColumnInfo(name="pay_location")
    val isPayAtLocation: Boolean? = null,
    @SerializedName("Title")
    @ColumnInfo(name="title")
    val title: String? = null
)