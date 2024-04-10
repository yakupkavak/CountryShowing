package com.example.countrynew.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrynew.model.Country
import com.example.countrynew.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel(){

    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable( )

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
                        countries.value = t
                        countryError.value = false
                        countryLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }

                })


        )

    }

}