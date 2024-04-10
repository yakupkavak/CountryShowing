package com.example.countrynew.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.countrynew.model.Country

@Dao()
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg countries: Country) : List<Long> //id'leri vericek

    @Query("SELECT * FROM countries")
    suspend fun getAllCountries() : ArrayList<Country>

    @Query("SELECT * FROM countries WHERE uuid = :countryId")
    suspend fun getCountry(countryId : Int) : Country

    @Query("DELETE FROM countries")
    suspend fun deleteAllCountries()
}