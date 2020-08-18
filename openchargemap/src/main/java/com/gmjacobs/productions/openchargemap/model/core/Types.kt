package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Types(
    @SerializedName("ChargePoint")
    val chargePoint: ChargePoint = ChargePoint(),
    @SerializedName("ChargerTypes")
    val chargerTypes: List<ChargerType> = listOf(),
    @SerializedName("CheckinStatusTypes")
    val checkinStatusTypes: List<CheckinStatusType> = listOf(),
    @SerializedName("ConnectionTypes")
    val connectionTypes: List<ConnectionType> = listOf(),
    @SerializedName("Countries")
    val countries: List<Country> = listOf(),
    @SerializedName("CurrentTypes")
    val currentTypes: List<CurrentType> = listOf(),
    @SerializedName("DataProviders")
    val dataProviders: List<DataProvider> = listOf(),
    @SerializedName("DataTypes")
    val dataTypes: List<DataType> = listOf(),
    @SerializedName("MetadataGroups")
    val metadataGroups: List<MetadataGroup> = listOf(),
    @SerializedName("Operators")
    val operators: List<Operator> = listOf(),
    @SerializedName("StatusTypes")
    val statusTypes: List<StatusType> = listOf(),
    @SerializedName("SubmissionStatusTypes")
    val submissionStatusTypes: List<SubmissionStatusType> = listOf(),
    @SerializedName("UsageTypes")
    val usageTypes: List<UsageType> = listOf(),
    @SerializedName("UserComment")
    val userComment: UserComment = UserComment(),
    @SerializedName("UserCommentTypes")
    val userCommentTypes: List<UserCommentType> = listOf(),
    @SerializedName("UserProfile")
    val userProfile: Any? = null
)