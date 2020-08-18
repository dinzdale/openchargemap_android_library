package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MetadataGroup(
    @SerializedName("DataProviderID")
    val dataProviderID: Int = 0,
    @SerializedName("ID")
    val iD: Int = 0,
    @SerializedName("IsPublicInterest")
    val isPublicInterest: Boolean = false,
    @SerializedName("IsRestrictedEdit")
    val isRestrictedEdit: Boolean = false,
    @SerializedName("MetadataFields")
    val metadataFields: List<MetadataField> = listOf(),
    @SerializedName("Title")
    val title: String = ""
)