package com.gmjacobs.productions.openchargemap.network

import android.content.Context
import com.gmjacobs.productions.openchargemap.R
import com.gmjacobs.productions.openchargemap.model.core.Types
import com.gmjacobs.productions.openchargemap.model.poi.PoiItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.RoundingMode
import java.text.DecimalFormat

class Api(val context: Context) {

    private val tag = Api::class.java.simpleName

    private val domain = context.getString(R.string.schema)
    private val client: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(domain).client(client).addConverterFactory(GsonConverterFactory.create()).build()
    private val apiService: IApi = retrofit.create(IApi::class.java)


    @Throws(Exception::class)
    suspend fun getCoreData(): Types {
        val map = hashMapOf<String, String>(
            "key" to context.getString(R.string.api_key)
        )
        return apiService.getCoreData(map)
    }

    @Throws(Exception::class)
    suspend fun getPOIs(lat: Double, lon: Double, radiusMiles: Int, maxResults: Int): List<PoiItem> {
        val map = hashMapOf<String, String>(
            "key" to context.getString(R.string.api_key),
            "countrycode" to "US",
            "latitude" to lat.roundUp(5).toString(),
            "longitude" to lon.roundUp(5).toString(),
            "distance" to radiusMiles.toString(),
            "maxresults" to maxResults.toString()
        )
        return apiService.getPOIs(map)
    }

    // Round up lat, lon to minimal number of places for api call
    fun Double.roundUp(decPlaces: Int): Double {
        val formatS = "#.${"#".repeat(decPlaces)}"
        val decFormat = DecimalFormat(formatS)
        decFormat.roundingMode = RoundingMode.CEILING
        return decFormat.format(this).toDouble()
    }
}
