package com.gmjacobs.productions.openchargemap.db

import androidx.room.*
import com.gmjacobs.productions.openchargemap.model.core.ChargerType

@Dao
interface ChargerTypeDao {

    @Query("delete from charger_type")
    suspend fun deletTable()

    @Insert
    suspend fun insertChargerTypes(chargerType: List<ChargerType>)

    @Update
    suspend fun updateChargerType(chargerType: ChargerType)

    @Delete
    suspend fun deleteChargerType(chargerType: ChargerType)

    @Query("Select * from charger_type")
    suspend fun getChargerTypes(): List<ChargerType>

    @Query("select * from charger_type where title like '%'||:name||'%'")
    suspend fun getChargerTypeByName(name: String): ChargerType?
}