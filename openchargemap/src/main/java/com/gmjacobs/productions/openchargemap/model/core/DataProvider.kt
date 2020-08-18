package com.gmjacobs.productions.openchargemap.model.core


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Keep
@Entity(tableName = "data_provider")
data class DataProvider(
    @SerializedName("Comments")
    @ColumnInfo(name = "comments")
    val comments: String? = null,
    @SerializedName("DataProviderStatusType")
    @ColumnInfo(name = "data_provider_status_type")
    val dataProviderStatusType: DataProviderStatusType? = null,
    @SerializedName("DateLastImported")
    @ColumnInfo(name = "date_last_imported")
    val dateLastImported: Date? = null,
    @SerializedName("ID")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val iD: Int = 0,
    @SerializedName("IsApprovedImport")
    @ColumnInfo(name="approved_import")
    val isApprovedImport: Boolean? = null,
    @SerializedName("IsOpenDataLicensed")
    @ColumnInfo(name="open_data_licensed")
    val isOpenDataLicensed: Boolean? = null,
    @ColumnInfo(name="restricted_edit")
    @SerializedName("IsRestrictedEdit")
    val isRestrictedEdit: Boolean? = false,
    @SerializedName("License")
    @ColumnInfo(name = "license")
    val license: String? = null,
    @SerializedName("Title")
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "website_url")
    @SerializedName("WebsiteURL")
    val websiteURL: String? = null
)