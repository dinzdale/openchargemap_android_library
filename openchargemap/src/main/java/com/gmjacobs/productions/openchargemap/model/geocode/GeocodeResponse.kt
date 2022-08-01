package com.gmjacobs.productions.openchargemap.model.geocode


import com.google.gson.annotations.SerializedName

data class GeocodeResponse(
    @SerializedName("data")
    val `data`: Data?
)