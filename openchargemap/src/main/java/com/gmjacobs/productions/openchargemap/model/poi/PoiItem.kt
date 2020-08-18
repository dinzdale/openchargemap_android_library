package com.gmjacobs.productions.openchargemap.model.poi


import androidx.annotation.Keep
import com.gmjacobs.productions.openchargemap.model.core.*
import com.google.gson.annotations.SerializedName

@Keep
data class PoiItem(
    @SerializedName("AddressInfo")
    val addressInfo: AddressInfo? = null,
    @SerializedName("Connections")
    val connections: List<Connection>? = null,
    @SerializedName("DataProvider")
    val dataProvider: DataProvider?=null,
    @SerializedName("DataProviderID")
    val dataProviderID: Int,
    @SerializedName("DataProvidersReference")
    val dataProvidersReference: Any,
    @SerializedName("DataQualityLevel")
    val dataQualityLevel: Int,
    @SerializedName("DateCreated")
    val dateCreated: String,
    @SerializedName("DateLastConfirmed")
    val dateLastConfirmed: Any,
    @SerializedName("DateLastStatusUpdate")
    val dateLastStatusUpdate: String,
    @SerializedName("DateLastVerified")
    val dateLastVerified: String,
    @SerializedName("DatePlanned")
    val datePlanned: Any,
    @SerializedName("GeneralComments")
    val generalComments: String,
    @SerializedName("ID")
    val iD: Int,
    @SerializedName("IsRecentlyVerified")
    val isRecentlyVerified: Boolean,
    @SerializedName("MediaItems")
    val mediaItems: Any,
    @SerializedName("MetadataValues")
    val metadataValues: Any,
    @SerializedName("NumberOfPoints")
    val numberOfPoints: Int,
    @SerializedName("OperatorID")
    val operatorID: Int,
    @SerializedName("OperatorInfo")
    val operatorInfo: OperatorInfo,
    @SerializedName("OperatorsReference")
    val operatorsReference: String,
    @SerializedName("ParentChargePointID")
    val parentChargePointID: Any,
    @SerializedName("PercentageSimilarity")
    val percentageSimilarity: Any,
    @SerializedName("StatusType")
    val statusType: StatusType,
    @SerializedName("StatusTypeID")
    val statusTypeID: Int,
    @SerializedName("SubmissionStatus")
    val submissionStatus: SubmissionStatus,
    @SerializedName("SubmissionStatusTypeID")
    val submissionStatusTypeID: Int,
    @SerializedName("UUID")
    val uUID: String,
    @SerializedName("UsageCost")
    val usageCost: String,
    @SerializedName("UsageType")
    val usageType: UsageType?,
    @SerializedName("UsageTypeID")
    val usageTypeID: Int,
    @SerializedName("UserComments")
    val userComments: Any
)