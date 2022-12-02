package com.gmjacobs.productions.openchargemap.model.geocode


import com.google.gson.annotations.SerializedName

data class ForwardGeocodeResponse(
    @SerializedName("data")
    val data: Data?
)

data class Data(
    @SerializedName("results")
    val results: List<GeocodeResult>?
)

data class GeocodeResult(
    @SerializedName("administrative_area")
    val administrativeArea: Any?,
    @SerializedName("confidence")
    val confidence: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("map_url")
    val mapUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("neighbourhood")
    val neighbourhood: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("postal_code")
    val postalCode: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("region_code")
    val regionCode: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("type")
    val type: String
)

