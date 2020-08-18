package com.gmjacobs.productions.openchargemap.db

import androidx.room.*
import com.gmjacobs.productions.openchargemap.model.core.CurrentType

@Dao
interface CurrentTypeDao {
    @Query("delete from current_type")
    suspend fun deletTable()

    @Insert
    suspend fun insertChargerTypes(currentType: List<CurrentType>)

    @Update
    suspend fun updateChargerType(currentType: CurrentType)

    @Delete
    suspend fun deleteChargerType(currentType: CurrentType)

    @Query("Select * from current_type")
    suspend fun getCurrentTypes(): List<CurrentType>

    @Query("Select * from current_type where id = :id")
    suspend fun getCurrentType(id: Int): CurrentType
}