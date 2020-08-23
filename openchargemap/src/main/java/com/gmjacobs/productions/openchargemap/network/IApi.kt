package com.gmjacobs.productions.openchargemap.network

import com.gmjacobs.productions.openchargemap.model.core.Types
import com.gmjacobs.productions.openchargemap.model.poi.PoiItem
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

interface IApi {

    // POI's
    @GET("/v3/poi/")
    suspend fun getPOIs(
        @HeaderMap headerMap: HashMap<String, String>,
        @QueryMap(encoded = true) queryMap: HashMap<String, String>
    ): List<PoiItem>

    // Core Data
    @GET("/v3/referencedata")
    suspend fun getCoreData(@HeaderMap headerMap: HashMap<String, String>): Types

}