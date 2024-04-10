package com.example.countrynew.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countrynew.databinding.CountryItemBinding
import com.example.countrynew.model.Country
import com.example.countrynew.util.createImageDownload
import com.example.countrynew.util.placeHolderProgressBar
import com.example.countrynew.view.FeedFragmentDirections

class CountryAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryView>() {

    class CountryView(val binding: CountryItemBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryView {
        val binding = CountryItemBinding.inflate(LayoutInflater.from(parent.context))
        return CountryView(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryView, position: Int) {
        holder.binding.countryName.text = countryList[position].countryName
        holder.binding.countryRegion.text = countryList[position].countryRegion
        holder.binding.imageView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.imageView.createImageDownload(countryList[position].countryFlagUrl,
            placeHolderProgressBar(holder.itemView.context)
        )
    }

    fun updateCountryList(newCountryList : ArrayList<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}