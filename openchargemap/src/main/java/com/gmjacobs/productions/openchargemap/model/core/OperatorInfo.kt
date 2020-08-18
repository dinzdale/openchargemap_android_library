package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class OperatorInfo(
    @SerializedName("AddressInfo")
    val addressInfo: String? = null,
    @SerializedName("BookingURL")
    val bookingURL: String? = null,
    @SerializedName("Comments")
    val comments: String? = null,
    @SerializedName("ContactEmail")
    val contactEmail: String? = null,
    @SerializedName("FaultReportEmail")
    val faultReportEmail: String? = null,
    @SerializedName("ID")
    val iD: Int = 0,
    @SerializedName("IsPrivateIndividual")
    val isPrivateIndividual: Boolean? = null,
    @SerializedName("IsRestrictedEdit")
    val isRestrictedEdit: Boolean? = null,
    @SerializedName("PhonePrimaryContact")
    val phonePrimaryContact: String? = null,
    @SerializedName("PhoneSecondaryContact")
    val phoneSecondaryContact: String? = null,
    @SerializedName("Title")
    val title: String? = null,
    @SerializedName("WebsiteURL")
    val websiteURL: String? = null
)