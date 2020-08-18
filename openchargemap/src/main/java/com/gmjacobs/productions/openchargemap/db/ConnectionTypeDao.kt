package com.gmjacobs.productions.openchargemap.db

import androidx.room.*
import com.gmjacobs.productions.openchargemap.model.core.ConnectionType

@Dao
interface ConnectionTypeDao {

    @Query("delete from connection_type" )
    suspend fun deletTable()

    @Insert
    suspend fun insertConnectionTypes(connectionTypes: List<ConnectionType>)

    @Update
    suspend fun updateConnectionrType(connectionType: ConnectionType)

    @Delete
    suspend fun deleteConnectionType(connectionType: ConnectionType)

    @Query("Select * from connection_type")
    suspend fun getConnectionTypes(): List<ConnectionType>
}