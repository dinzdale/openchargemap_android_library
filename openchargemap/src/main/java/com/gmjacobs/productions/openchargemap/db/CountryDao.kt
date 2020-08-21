package com.gmjacobs.productions.openchargemap.db

import androidx.room.*
import com.gmjacobs.productions.openchargemap.model.core.Country

@Dao
interface CountryDao {
    @Query("delete from country")
    suspend fun deletTable()

    @Insert
    suspend fun insertCountries(country: List<Country>)

    @Update
    suspend fun updateCountry(country: Country)

    @Delete
    suspend fun deleteCountry(country: Country)

    @Query("Select * from country")
    suspend fun getCountries(): List<Country>

    @Query("Select * from country where title like :name")
    suspend fun getCountryByName(name:String) : Country?
}