package com.example.countrynew.service

import android.content.Context
import android.graphics.ColorSpace.Model
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Model::class), version = 1)
abstract class CountryDatabase: RoomDatabase() {

    abstract fun countryDao(): CountryDao

    companion object{

        // bir kere başlangıçta null olarak geliyor bir scope içerisinde çalışıp
        // database tanımlanıyor başka bir scope içerisinde kullanılmaya çalışınca
        // synchonized lock ile tanımlandığı için sadece tek bir scope bıunu kullanıyor!!
        @Volatile private var instance : CountryDatabase? = null

        private val lock = Any()

        //bunun farklı scopelarda çalışıp çalışmadığını kontrol ediyoruz öncelikle
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            //senkronize olarak çalışacağını belirtiyoruz synchronized ile
            //sonra bunu database ile eşitliyoruz
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }



        private fun makeDatabase(context: Context) = Room.databaseBuilder(context,
            CountryDatabase::class.java,"countrydatabase").build()
    }
}