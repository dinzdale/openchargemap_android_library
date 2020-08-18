package com.gmjacobs.productions.openchargemap.db

import androidx.room.TypeConverter
import com.gmjacobs.productions.openchargemap.model.core.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class RoomConverters {

    //    @TypeConverter
    //    fun <T> toJson(c: T): String {
    //        val type = object : TypeToken<T>() {}.type
    //        return Gson().toJson(c)
    //    }
    //
    //    @TypeConverter
    //    fun <in T> fromJson(jsonS: String): T {
    //        val type = object : TypeToken<T>() {}.type
    //        return Gson().fromJson(jsonS, type)
    //    }

    @TypeConverter
    fun dataProviderFromJson(jsonS: String) = Gson().fromJson(jsonS, DataProvider::class.java)

    @TypeConverter
    fun dataProviderToJson(dataProvider: DataProvider) = Gson().toJson(dataProvider)

    @TypeConverter
    fun dataProviderTypeFromJson(jsonS: String) = Gson().fromJson(jsonS, DataProviderStatusType::class.java)

    @TypeConverter
    fun dataProviderTypeToJson(dataProviderStatusType: DataProviderStatusType) = Gson().toJson(dataProviderStatusType)


    @TypeConverter
    fun dateFromLong(value: Long?) = value?.let { Date(it) }


    @TypeConverter
    fun dateToLong(date: Date?) = date?.let { it.time }

    @TypeConverter
    fun currentTypeToJson(currentType: CurrentType) = Gson().toJson(currentType, CurrentType::class.java)

    @TypeConverter
    fun currentTypeFromJson(json: String) = Gson().fromJson(json, CurrentType::class.java)

    @TypeConverter
    fun statusTypeToJson(statusType: StatusType) = Gson().toJson(statusType, StatusType::class.java)

    @TypeConverter
    fun statusTypeFromJson(json: String) = Gson().fromJson(json, StatusType::class.java)

    @TypeConverter
    fun connectionTypeToJson(connectionType: ConnectionType) = Gson().toJson(connectionType, ConnectionType::class.java)

    @TypeConverter
    fun connectionTypeFromJson(json: String) = Gson().fromJson(json, ConnectionType::class.java)

    @TypeConverter
    fun addressInfoToJson(addressInfo: AddressInfo?) = addressInfo?.let { Gson().toJson(addressInfo, AddressInfo::class.java) }

    @TypeConverter
    fun addressInfoFromFromJson(json: String?) = json?.let { Gson().fromJson(json, AddressInfo::class.java) }

    @TypeConverter
    fun connectionToJson(connections: List<Connection>) = Gson().toJson(connections)

    @TypeConverter
    fun connectionFromFromJson(json: String): List<Connection> {
        val type = object : TypeToken<List<Connection>>() {}.type
        val connectionList: List<Connection> = Gson().fromJson(json, type)
        return connectionList
    }

    @TypeConverter
    fun operatorToJson(operator: Operator) = Gson().toJson(operator, Operator::class.java)

    @TypeConverter
    fun operatorFromJson(json: String) = Gson().fromJson(json, Operator::class.java)

    @TypeConverter
    fun operatorInfoToJson(operatorInfo: OperatorInfo) = Gson().toJson(operatorInfo, OperatorInfo::class.java)

    @TypeConverter
    fun operatorInfoFromJson(json: String) = Gson().fromJson(json, OperatorInfo::class.java)

    @TypeConverter
    fun usageTypeToJson(usageType: UsageType) = Gson().toJson(usageType, UsageType::class.java)

    @TypeConverter
    fun usageTypeFromJson(json: String) = Gson().fromJson(json, UsageType::class.java)

}
