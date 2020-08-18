package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Keep
@Entity(tableName = "chargepoint")
data class ChargePoint(
    @SerializedName("AddressInfo")
    @ColumnInfo(name="address_info")
    val addressInfo: AddressInfo? = AddressInfo(),
    @SerializedName("Connections")
    @ColumnInfo(name="connections")
    val connections: List<Connection> = listOf(),
    @SerializedName("DataProvider")
    @ColumnInfo(name="data_provider")
    val dataProvider: DataProvider = DataProvider(),
    @SerializedName("DataProviderID")
    @ColumnInfo(name="data_provider_id")
    val dataProviderID: Int? = null,
    @SerializedName("DataProvidersReference")
    @ColumnInfo(name="data_provider_reference")
    val dataProvidersReference: Int? = null,
    @SerializedName("DataQualityLevel")
    @ColumnInfo(name="data_quality_level")
    val dataQualityLevel: Int = 0,
    @SerializedName("DateCreated")
    @ColumnInfo(name="date_created")
    val dateCreated: Date = Date(),
    @SerializedName("DateLastConfirmed")
    @ColumnInfo(name="date_last_confirmed")
    val dateLastConfirmed: Date = Date(),
    @SerializedName("DateLastStatusUpdate")
    @ColumnInfo(name="date_last_status_update")
    val dateLastStatusUpdate: String = "",
    @SerializedName("DateLastVerified")
    @ColumnInfo(name="date_last_status_verified")
    val dateLastVerified: String = "",
    @SerializedName("DatePlanned")
    @ColumnInfo(name="date_last_status_planned")
    val datePlanned: Date? = null,
    @SerializedName("GeneralComments")
    @ColumnInfo(name="general_comments")
    val generalComments: String = "",
    @SerializedName("ID")
    @PrimaryKey
    @ColumnInfo(name="id")
    val iD: Int = 0,
    @SerializedName("IsRecentlyVerified")
    @ColumnInfo(name="recently_verified")
    val isRecentlyVerified: Boolean = false,
    @SerializedName("MediaItems")
    @ColumnInfo(name="has_media_items")
    val mediaItems:Boolean? = null,
    @SerializedName("MetadataValues")
    @ColumnInfo(name="metadata_values")
    val metadataValues: Int? = null,
    @SerializedName("NumberOfPoints")
    @ColumnInfo(name="number_of_points")
    val numberOfPoints: Int = 0,
    @SerializedName("OperatorID")
    @ColumnInfo(name="operator_id")
    val operatorID: Int? = null,
    @SerializedName("OperatorInfo")
    @ColumnInfo(name="operator_info")
    val operatorInfo: OperatorInfo = OperatorInfo(),
    @SerializedName("OperatorsReference")
    @ColumnInfo(name="operator_reference")
    val operatorsReference: Int? = null,
    @SerializedName("ParentChargePointID")
    @ColumnInfo(name="parent_charge_point_id")
    val parentChargePointID: Int? = null,
    @SerializedName("PercentageSimilarity")
    @ColumnInfo(name="percentage_similarity")
    val percentageSimilarity: Double? = null,
    @SerializedName("StatusType")
    @ColumnInfo(name="status_type")
    val statusType: StatusType = StatusType(),
    @SerializedName("StatusTypeID")
    @ColumnInfo(name="status_id")
    val statusTypeID: Int? = null,
    @SerializedName("SubmissionStatus")
    @ColumnInfo(name="submission_status")
    val submissionStatus: Int? = null,
    @SerializedName("SubmissionStatusTypeID")
    @ColumnInfo(name="submission_status_type_id")
    val submissionStatusTypeID: Int? = null,
    @SerializedName("UUID")
    @ColumnInfo(name="uuid")
    val uUID: String = "",
    @SerializedName("UsageCost")
    @ColumnInfo(name="usage_cost")
    val usageCost: Double? = null,
    @SerializedName("UsageType")
    @ColumnInfo(name="usage_type")
    val usageType: UsageType = UsageType(),
    @SerializedName("UsageTypeID")
    @ColumnInfo(name="usage_type_id")
    val usageTypeID: Int? = null,
    @SerializedName("UserComments")
    @ColumnInfo(name="user_comments")
    val userComments: String? = null
)