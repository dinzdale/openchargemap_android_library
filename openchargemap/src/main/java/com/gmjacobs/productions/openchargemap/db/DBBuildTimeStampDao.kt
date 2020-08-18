package com.gmjacobs.productions.openchargemap.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gmjacobs.productions.openchargemap.model.DBBuildTimeStamp

@Dao
interface DBBuildTimeStampDao {
    @Query("delete from db_build_timestamp")
    suspend fun deletTable()

    @Insert
    suspend fun insertBuildTimeStamp(dbBuildTimeStamp: DBBuildTimeStamp)

    @Query("select * from db_build_timestamp")
    suspend fun getBuildTimeStamp(): DBBuildTimeStamp

    // select count(*) from sqlite_master where type='table' and name='db_build_timestamp';
    @Query("select count(*) from 'sqlite_master' where type = 'table' and name = 'db_build_timestamp'")
    suspend fun tableExists(): Int

    @Query("select count(*) from db_build_timestamp")
    suspend fun rowsExist(): Int


}