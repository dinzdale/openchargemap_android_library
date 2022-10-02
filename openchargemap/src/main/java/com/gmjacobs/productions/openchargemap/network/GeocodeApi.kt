package com.gmjacobs.productions.openchargemap.network

import android.content.Context
import com.gmjacobs.productions.openchargemap.R
import com.gmjacobs.productions.openchargemap.model.geocode.GeocodeForwardResponseItem
import com.gmjacobs.productions.openchargemap.BuildConfig
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GeocodeApi(val context: Context) {

    private val tag = GeocodeApi::class.java.simpleName

//    private val domain = context.getString(R.string.position_stack_base_url)
//    private val api_key = context.getString(R.string.postion_stack_api_key)
    private val domain = context.getString(R.string.geocode_maps_base_url)

    private val client: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor).addInterceptor(OkHttpProfilerInterceptor())
        }
        builder.build()
    }
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(domain).client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val apiService: IGeocode = retrofit.create(IGeocode::class.java)
//    private val headerMap = hashMapOf<String, String>(
//        "User-Agent" to context.applicationInfo.packageName,
//        "X-API-Key" to context.getString(R.string.api_key)
//    )

    enum class DistanceUnit(val unitS: String) {
        MILES("Miles"),
        KILOS("KM")
    }


    suspend fun geocodeForward(
        query: String = "1600 Pennsylvania Ave NW, Washington DC"): Result<List<GeocodeForwardResponseItem>> {
        return try {
            Result.success(apiService.geocodeForward(hashMapOf("q" to query)))
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

//    suspend fun reverseGeocode(lat: Float, lon: Float): Result<GeocodeResponse?> {
//        return try {
//            val result =
//                apiService.geocode(hashMapOf("access_key" to api_key, "query" to "${lat},${lon}"))
//            Result.success(result)
//        } catch (ex: Exception) {
//            Result.failure(ex)
//        }
//    }


}
