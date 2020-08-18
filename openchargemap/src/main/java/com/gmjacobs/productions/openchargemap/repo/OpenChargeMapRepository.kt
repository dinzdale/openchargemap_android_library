package com.gmjacobs.productions.openchargemap.repo

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.gmjacobs.productions.openchargemap.db.OpenChargeMapDB
import com.gmjacobs.productions.openchargemap.model.DBBuildTimeStamp
import com.gmjacobs.productions.openchargemap.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

class OpenChargeMapRepository(val applicationContext: Context) : CoroutineScope by GlobalScope {

    private val tag = OpenChargeMapRepository::class.java.simpleName

    private val openChargeDB: OpenChargeMapDB by lazy {
        Room.databaseBuilder(applicationContext, OpenChargeMapDB::class.java, "openchargemap.db").build()
    }
    private val api: Api by lazy {
        Api(applicationContext)
    }


    @Throws(Exception::class)
    suspend fun loadDb(noDaysExpiration: Int = 10) {
        if (loadOrRefreshDB(noDaysExpiration)) {
            Log.d(tag, "loading db")
            val types = api.getCoreData()
            openChargeDB.dbBuildTimeStampDao().deletTable()
            openChargeDB.dbBuildTimeStampDao().insertBuildTimeStamp(DBBuildTimeStamp(Date()))
            openChargeDB.chargerTypeDao().deletTable()
            openChargeDB.chargerTypeDao().insertChargerTypes(types.chargerTypes)
            openChargeDB.connectionTypeDao().deletTable()
            openChargeDB.connectionTypeDao().insertConnectionTypes(types.connectionTypes)
            openChargeDB.dataProviderDao().deletTable()
            openChargeDB.dataProviderDao().insertDataProviders(types.dataProviders)
            openChargeDB.countryDao().deletTable()
            openChargeDB.countryDao().insertCountries(types.countries)
            openChargeDB.currentTypeDao().deletTable()
            openChargeDB.currentTypeDao().insertChargerTypes(types.currentTypes)
            openChargeDB.statusTypeDao().deletTable()
            openChargeDB.statusTypeDao().insertStatusTypes(types.statusTypes)
            openChargeDB.chargePointDao().deletTable()
            openChargeDB.chargePointDao().insertChargePoint(types.chargePoint)
            openChargeDB.operatorDao().deletTable()
            openChargeDB.operatorDao().insertOperators(types.operators)
            openChargeDB.submissionStatusTypeDao().deletTable()
            openChargeDB.submissionStatusTypeDao().insertSubmissionStatusTypes(types.submissionStatusTypes)
            openChargeDB.usageTypeDao().deletTable()
            openChargeDB.usageTypeDao().insertUsageTypes(types.usageTypes)
        }
        else {
            Log.d(tag, "NOT, loading db")
        }

    }

    suspend fun loadOrRefreshDB(noDaysExpiration: Int): Boolean {
        return if (openChargeDB.dbBuildTimeStampDao().tableExists() > 0 && openChargeDB.dbBuildTimeStampDao().rowsExist() > 0) {
            // check for expired number of days
            val now = ZonedDateTime.now(ZoneId.of("UTC"))
            val dbTimeStampInstant = Instant.ofEpochMilli(openChargeDB.dbBuildTimeStampDao().getBuildTimeStamp().timeStampDate.time)
            val dbTimeStampZonedTime = ZonedDateTime.ofInstant(dbTimeStampInstant, ZoneId.of("UTC"))
            Log.d(tag, "loadOrRefreshDB days diff: ${Duration.between(dbTimeStampZonedTime, now).toDays()}")
            if (Duration.between(dbTimeStampZonedTime, now).toDays() >= noDaysExpiration) {
                true
            }
            else {
                false
            }
        }
        else true
    }


    suspend fun getChargeTypes() = openChargeDB.chargerTypeDao().getChargerTypes()
    suspend fun getConnectionTypes() = openChargeDB.connectionTypeDao().getConnectionTypes()
    suspend fun getDataProviders() = openChargeDB.dataProviderDao().getDataProviders()
    suspend fun getCountries() = openChargeDB.countryDao().getCountries()
    suspend fun getCurrentTypes() = openChargeDB.currentTypeDao().getCurrentTypes()
    suspend fun getCurrentType(id: Int) = openChargeDB.currentTypeDao().getCurrentType(id)
    suspend fun getStatusTypes() = openChargeDB.statusTypeDao().getStatusTypes()
    suspend fun getChargePoint() = openChargeDB.chargePointDao().getChargePoint()
    suspend fun getOperators() = openChargeDB.operatorDao().getOperators()
    suspend fun getSubmissionStatusTypes() = openChargeDB.submissionStatusTypeDao().getSubmissionStatusTypes()
    suspend fun getUsageTypes() = openChargeDB.usageTypeDao().getUsageTypes()

    // POIS
    suspend fun getPOIs(lat: Double, lon: Double, radiusInMiles: Int, maxResults: Int) = api.getPOIs(lat, lon, radiusInMiles, maxResults)
}