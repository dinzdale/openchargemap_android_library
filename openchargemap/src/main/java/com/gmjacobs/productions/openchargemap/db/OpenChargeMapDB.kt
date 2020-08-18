package com.gmjacobs.productions.openchargemap.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmjacobs.productions.openchargemap.model.DBBuildTimeStamp
import com.gmjacobs.productions.openchargemap.model.core.*

@Database(
    entities = arrayOf(
        ChargerType::class,
        ConnectionType::class,
        DataProvider::class,
        Country::class,
        CurrentType::class,
        StatusType::class,
        ChargePoint::class,
        Operator::class,
        SubmissionStatusType::class,
        UsageType::class,
        DBBuildTimeStamp::class
    ), version = 1, exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class OpenChargeMapDB : RoomDatabase() {
    abstract fun dbBuildTimeStampDao(): DBBuildTimeStampDao
    abstract fun chargerTypeDao(): ChargerTypeDao
    abstract fun connectionTypeDao(): ConnectionTypeDao
    abstract fun dataProviderDao(): DataProviderDao
    abstract fun countryDao(): CountryDao
    abstract fun currentTypeDao(): CurrentTypeDao
    abstract fun statusTypeDao(): StatusTypeDao
    abstract fun chargePointDao(): ChargePointDao
    abstract fun operatorDao(): OperatorDao
    abstract fun submissionStatusTypeDao(): SubmissionStatusTypeDao
    abstract fun usageTypeDao(): UsageTypeDao
}