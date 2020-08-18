package com.gmjacobs.productions.openchargemap.network

import com.gmjacobs.productions.openchargemap.model.core.Types
import com.gmjacobs.productions.openchargemap.model.poi.PoiItem
import retrofit2.http.*

interface IApi {

    // POI's
    @GET("/v3/poi/")
    suspend fun getPOIs(@QueryMap queryMap: HashMap<String,String>) : List<PoiItem>

    // Core Data
    @GET("/v3/referencedata")
    suspend fun getCoreData(@QueryMap queryMap: HashMap<String,String>) : Types

}