package com.gmjacobs.productions.openchargemap.db

import androidx.room.*
import com.gmjacobs.productions.openchargemap.model.core.ChargerType
import com.gmjacobs.productions.openchargemap.model.core.Operator

@Dao
interface OperatorDao {
    @Query("delete from operator")
    suspend fun deletTable()

    @Insert
    suspend fun insertOperators(operators: List<Operator>)

    @Update
    suspend fun updateOperator(operator: Operator)

    @Delete
    suspend fun deleteOperator(operator: Operator)

    @Query("Select * from operator")
    suspend fun getOperators(): List<Operator>
}