package com.gmjacobs.productions.openchargemap.db

import androidx.room.*
import com.gmjacobs.productions.openchargemap.model.core.StatusType
import com.gmjacobs.productions.openchargemap.model.core.UsageType

@Dao
interface UsageTypeDao {
    @Query("delete from usage_type")
    suspend fun deletTable()

    @Insert
    suspend fun insertUsageTypes(usageTypes: List<UsageType>)

    @Update
    suspend fun updateUsageType(usageType: UsageType)

    @Delete
    suspend fun deleteUsageType(usageType: UsageType)

    @Query("Select * from usage_type")
    suspend fun getUsageTypes(): List<UsageType>
}