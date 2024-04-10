package com.example.countrynew.service

import com.example.countrynew.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {

    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    private val BASE_URL = "https://raw.githubusercontent.com/"
    //api tanımlayıp nerede ne kullanacağını yazıyoruz
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountryAPI::class.java)

    fun getData(): Single<ArrayList<Country>>{
        return api.getCountries()
    }

}