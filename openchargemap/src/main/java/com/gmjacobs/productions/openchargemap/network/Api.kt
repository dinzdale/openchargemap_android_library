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
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(domain).client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val apiService: IApi = retrofit.create(IApi::class.java)
    private val headerMap = hashMapOf<String, String>(
        "User-Agent" to context.applicationInfo.packageName,
        "X-API-Key" to context.getString(R.string.api_key)
    )

    enum class DistanceUnit(val unitS: String) {
        MILES("Miles"),
        KILOS("KM")
    }


    @Throws(Exception::class)
    suspend fun getCoreData(): Types {
        return apiService.getCoreData(headerMap)
    }

    @Throws(Exception::class)
    suspend fun getPOIs(
        lat: Double, lon: Double, radiusMiles: Int,
        countryIDs: List<Int>? = null,
        operatorIDs: List<Int>? = null,
        connectionTypeIDs: List<Int>? = null,
        usageTypeIDs: List<Int>? = null,
        statusTypeIDs: List<Int>? = null,
        units: DistanceUnit,
        maxResults: Int,
        compact: Boolean,
        verbose: Boolean
    ): List<PoiItem> {
        val queryMap = hashMapOf<String, String>(
            "latitude" to lat.roundUp(5).toString(),
            "longitude" to lon.roundUp(5).toString(),
            "distance" to radiusMiles.toString(),
            "distanceUnit" to units.unitS,
            "maxresults" to maxResults.toString(),
            "compact" to compact.toString(),
            "verbose" to verbose.toString()
        )
        countryIDs?.let {
            queryMap.put("countryid", it.commaSeperated())
        }
        operatorIDs?.let {
            queryMap.put("operatorid", it.commaSeperated())
        }
        usageTypeIDs?.let {
            queryMap.put("usagetypeid", it.commaSeperated())
        }
        statusTypeIDs?.let {
            queryMap.put("statustypeid", it.commaSeperated())
        }
        connectionTypeIDs?.let {
            queryMap.put("connectiontypeid", it.commaSeperated())
        }
        return apiService.getPOIs(headerMap, queryMap)
    }

    // Round up lat, lon to minimal number of places for api call
    fun Double.roundUp(decPlaces: Int): Double {
        val formatS = "#.${"#".repeat(decPlaces)}"
        val decFormat = DecimalFormat(formatS)
        decFormat.roundingMode = RoundingMode.CEILING
        return decFormat.format(this).toDouble()
    }

    fun List<Int>.commaSeperated(): String {
        return when {
            size == 0 -> ""
            size == 1 -> "${get(0)}"
            else -> {
                val sb = StringBuilder()
                for (i in 0 until lastIndex) {
                    sb.append("${get(i)},")
                }
                if (lastIndex > 0) {
                    sb.append("${get(lastIndex)}")
                }
                sb.toString()
            }
        }
    }
}

