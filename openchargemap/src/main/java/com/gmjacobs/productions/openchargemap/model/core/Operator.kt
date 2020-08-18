package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName= "operator")
data class Operator(
    @SerializedName("AddressInfo")
    @ColumnInfo(name = "address_info")
    val addressInfo: AddressInfo? = null,
    @SerializedName("BookingURL")
    @ColumnInfo(name = "booking_url")
    val bookingURL: String? = null,
    @SerializedName("Comments")
    @ColumnInfo(name = "comments")
    val comments: String? = null,
    @SerializedName("ContactEmail")
    @ColumnInfo(name = "contact_email")
    val contactEmail: String? = null,
    @SerializedName("FaultReportEmail")
    @ColumnInfo(name = "full_report_email")
    val faultReportEmail: String? = null,
    @SerializedName("ID")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val iD: Int = 0,
    @SerializedName("IsPrivateIndividual")
    @ColumnInfo(name = "private_individual")
    val isPrivateIndividual: Boolean = false,
    @SerializedName("IsRestrictedEdit")
    @ColumnInfo(name = "restricted_edit")
    val isRestrictedEdit: Boolean? = null,
    @SerializedName("PhonePrimaryContact")
    @ColumnInfo(name = "phone_primary_contact")
    val phonePrimaryContact: String? = null,
    @SerializedName("PhoneSecondaryContact")
    @ColumnInfo(name = "phone_secondary_contact")
    val phoneSecondaryContact: String? = null,
    @SerializedName("Title")
    @ColumnInfo(name = "title")
    val title: String = "",
    @SerializedName("WebsiteURL")
    @ColumnInfo(name = "website_url")
    val websiteURL: String? = null
)