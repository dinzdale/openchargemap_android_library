package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "submission_status_type")
data class SubmissionStatusType(
    @SerializedName("ID")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val iD: Int = 0,
    @SerializedName("IsLive")
    @ColumnInfo(name = "live")
    val isLive: Boolean = false,
    @SerializedName("Title")
    @ColumnInfo(name = "title")
    val title: String = ""
)