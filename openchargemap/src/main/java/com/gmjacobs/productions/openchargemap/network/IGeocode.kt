package com.gmjacobs.productions.openchargemap.network

import com.gmjacobs.productions.openchargemap.model.geocode.GeocodeResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IGeocode {
    @GET("/forward")
    suspend fun geocode(@QueryMap(encoded = true) queryMap:HashMap<String,String>) : GeocodeResponse

    @GET("/reverse")
    suspend fun reverseGeocode(@QueryMap(encoded = true) queryMap:HashMap<String,String>) : GeocodeResponse
}