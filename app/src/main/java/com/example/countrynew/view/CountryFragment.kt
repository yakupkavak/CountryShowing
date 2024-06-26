package com.example.countrynew.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.countrynew.R
import com.example.countrynew.databinding.FragmentCountryBinding
import com.example.countrynew.model.Country
import com.example.countrynew.util.createImageDownload
import com.example.countrynew.util.placeHolderProgressBar
import com.example.countrynew.viewModel.CountryViewModel

class CountryFragment : Fragment() {

    //private var _binding : FragmentCountryBinding? = null
    //private val binding get() = _binding!!
    private lateinit var binding : FragmentCountryBinding
    private var countryUuid = 0;
    private lateinit var viewModel : CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            println("uuid girdi")
            countryUuid = CountryFragmentArgs.fromBundle(it).uuid
        }

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }
    private fun observeLiveData(){
          viewModel.countryLiveData.observe(viewLifecycleOwner){
              it?.let {
                  binding.country = it
                  /*
                  binding.fragmentCountryCapital.text = it.countryCapital
                  binding.fragmentCountryCurrency.text = it.countryCurrency
                  binding.fragmentCountryLanguage.text = it.countryLanguage
                  binding.fragmentCountryName.text = it.countryName
                  binding.fragmentCountryRegion.text = it.countryRegion
                  binding.fragmentCountryView.createImageDownload(it.countryFlagUrl,
                      placeHolderProgressBar(requireContext())
                  )
                   */
              }
          }
    }


}