package com.gmjacobs.productions.openchargemap.db

import androidx.room.*
import com.gmjacobs.productions.openchargemap.model.core.StatusType

@Dao
interface StatusTypeDao {
    @Query("delete from status_type")
    suspend fun deletTable()

    @Insert
    suspend fun insertStatusTypes(statusType: List<StatusType>)

    @Update
    suspend fun updateStatisType(chargerType: StatusType)

    @Delete
    suspend fun deleteStatusType(chargerType: StatusType)

    @Query("Select * from status_type")
    suspend fun getStatusTypes(): List<StatusType>
}