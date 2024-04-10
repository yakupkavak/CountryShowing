package com.example.countrynew.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrynew.adapter.CountryAdapter
import com.example.countrynew.databinding.FragmentFeedBinding
import com.example.countrynew.viewModel.FeedViewModel

class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()
        binding.countryRecyclerList.layoutManager = LinearLayoutManager(context)
        binding.countryRecyclerList.adapter = countryAdapter

        binding.refreshLayout.setOnRefreshListener { //kullanıcı refresh edince ne olucak
            binding.countryRecyclerList.visibility = View.GONE
            binding.errorText.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            viewModel.refreshData()
            binding.refreshLayout.isRefreshing = false

        }

        observeLiveData()

     }




    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner){
            it?.let {countries ->
                binding.countryRecyclerList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        }

        viewModel.countryError.observe(viewLifecycleOwner){error ->
            error?.let {
                if (it){
                    binding.errorText.visibility = View.VISIBLE
                }
                else{
                    binding.errorText.visibility = View.GONE
                }}
        }

        viewModel.countryLoading.observe(viewLifecycleOwner){
            it?.let {
                if (it){
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                    binding.countryRecyclerList.visibility = View.GONE
                }
                else{
                    binding.progressBar.visibility = View.GONE
                }
            }
        }



    }












    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
 }