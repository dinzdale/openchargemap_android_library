package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MetadataField(
    @SerializedName("DataType")
    val dataType: Any? = null,
    @SerializedName("DataTypeID")
    val dataTypeID: Int = 0,
    @SerializedName("ID")
    val iD: Int = 0,
    @SerializedName("MetadataFieldOptions")
    val metadataFieldOptions: List<MetadataFieldOption>? = null,
    @SerializedName("MetadataGroupID")
    val metadataGroupID: Int = 0,
    @SerializedName("Title")
    val title: String = ""
)