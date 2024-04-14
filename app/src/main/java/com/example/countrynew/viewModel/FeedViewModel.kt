package com.example.countrynew.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrynew.model.Country
import com.example.countrynew.service.CountryAPIService
import com.example.countrynew.service.CountryDatabase
import com.example.countrynew.util.CustomSharedPreferences
import com.example.countrynew.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application){

    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())

    //dataların liveData tanımlayıp verileri bağlama işlemi burada yapılır
    val countries = MutableLiveData<ArrayList<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        /* val country = Country("turkey","asia","ankara","tl","türkçe","15421")
        val country1 = Country("turkey1","asi1a","ankar1a","1tl","türk1çe","15421")

        val countryList = arrayListOf<Country>(country,country1)
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false

         */
        getDataFromAPI()

    }

    private fun getDataFromAPI(){
        countryLoading.value = true
        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread()) //nerede çalıştıralacağı
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ArrayList<Country>>(){
                    override fun onSuccess(t: ArrayList<Country>) {
                        storeInSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }

                })


        )

    }

    private fun showCountries(countryList : ArrayList<Country>){
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

    private fun storeInSQLite(countryList : ArrayList<Country>){
        //INSERT ALL FONKSİYONU DATABASEYE VERİLEN OBJELERE OTOMATİK UUİD TANIMLIYOR
        //BU TANIMLANAN UUIDLERİ BİZLERİN countryList'ine ekleyip tanımlıyoruz
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*countryList.toTypedArray())
            var i = 0
            while (i < listLong.size){
                countryList[i].uuid = listLong[i].toInt()
                i ++
            }
            showCountries(countryList)
        }

        customSharedPreferences.saveTime(System.nanoTime())
    }

}