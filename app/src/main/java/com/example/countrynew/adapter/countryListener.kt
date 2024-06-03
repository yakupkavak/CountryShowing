package com.example.countrynew.adapter

import android.view.View
import java.util.UUID

public interface CountryListener{
    fun onCountryClicked(view:View,uuid: Int)
}