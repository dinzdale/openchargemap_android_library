package com.gmjacobs.productions.openchargemap.db

import android.graphics.Point
import androidx.room.*
import com.gmjacobs.productions.openchargemap.model.core.ChargePoint
import com.gmjacobs.productions.openchargemap.model.core.ChargerType

@Dao
interface ChargePointDao {
    @Query("delete from chargepoint" )
    suspend fun deletTable()

    @Insert
    suspend fun insertChargePoint(chargePoint: ChargePoint)

    @Update
    suspend fun updateChargePoint(chargePoint: ChargePoint)

    @Delete
    suspend fun deleteChargePoint(chargePoint: ChargePoint)

    @Query("Select * from chargepoint")
    suspend fun getChargePoint(): ChargePoint
}