package com.gmjacobs.productions.openchargemap.model.geocode


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("results")
    val results: List<GecodeResult>?
)