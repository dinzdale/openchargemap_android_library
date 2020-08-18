package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UserComment(
    @SerializedName("ChargePointID")
    val chargePointID: Int = 0,
    @SerializedName("CheckinStatusType")
    val checkinStatusType: CheckinStatusType = CheckinStatusType(),
    @SerializedName("CheckinStatusTypeID")
    val checkinStatusTypeID: Any? = null,
    @SerializedName("Comment")
    val comment: String = "",
    @SerializedName("CommentType")
    val commentType: CommentType = CommentType(),
    @SerializedName("CommentTypeID")
    val commentTypeID: Any? = null,
    @SerializedName("DateCreated")
    val dateCreated: String = "",
    @SerializedName("ID")
    val iD: Int = 0,
    @SerializedName("IsActionedByEditor")
    val isActionedByEditor: Any? = null,
    @SerializedName("Rating")
    val rating: Any? = null,
    @SerializedName("RelatedURL")
    val relatedURL: Any? = null,
    @SerializedName("User")
    val user: Any? = null,
    @SerializedName("UserName")
    val userName: Any? = null
)