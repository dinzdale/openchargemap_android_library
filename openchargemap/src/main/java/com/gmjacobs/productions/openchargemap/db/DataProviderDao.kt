package com.gmjacobs.productions.openchargemap.db

import androidx.room.*
import com.gmjacobs.productions.openchargemap.model.core.DataProvider

@Dao
interface DataProviderDao {
    @Query("delete from data_provider")
    suspend fun deletTable()

    @Insert
    suspend fun insertDataProviders(dataProviders: List<DataProvider>)

    @Update
    suspend fun updateDataProvider(dataProvider: DataProvider)

    @Delete
    suspend fun deleteDataProvider(dataProvider: DataProvider)

    @Query("Select * from data_provider")
    suspend fun getDataProviders(): List<DataProvider>
}