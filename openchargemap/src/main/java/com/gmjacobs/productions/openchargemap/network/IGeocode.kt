package com.gmjacobs.productions.openchargemap.network

import com.gmjacobs.productions.openchargemap.model.geocode.GeocodeForwardResponseItem
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IGeocode {
//    @GET("v1/forward")
//    suspend fun geocode(@QueryMap(encoded = true) queryMap:HashMap<String,String>) : ForwardGeocodeResponse
    @GET("search")
    suspend fun geocodeForward(@QueryMap(encoded = false) queryMap:HashMap<String,String>) : List<GeocodeForwardResponseItem>
//    @GET("/reverse")
//    suspend fun reverseGeocode(@QueryMap(encoded = true) queryMap:HashMap<String,String>) : GeocodeResponse
}