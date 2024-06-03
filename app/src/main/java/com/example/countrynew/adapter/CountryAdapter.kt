package com.example.countrynew.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countrynew.R
import com.example.countrynew.databinding.CountryItemBinding
import com.example.countrynew.model.Country
import com.example.countrynew.util.createImageDownload
import com.example.countrynew.util.placeHolderProgressBar
import com.example.countrynew.view.FeedFragmentDirections

class CountryAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryView>(),CountryListener {

    class CountryView(val view: CountryItemBinding): RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryView {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CountryItemBinding>(inflater,R.layout.country_item,parent,false)
        return CountryView(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryView, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this

        /*
        holder.binding.countryName.text = countryList[position].countryName
        holder.binding.countryRegion.text = countryList[position].countryRegion
        holder.binding.imageView.setOnClickListener {
            val myUUid = countryList[position].uuid
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(myUUid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.imageView.createImageDownload(countryList[position].countryFlagUrl,
            placeHolderProgressBar(holder.itemView.context)
        )
         */
    }

    fun updateCountryList(newCountryList : ArrayList<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(view: View, uuid: Int) {
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(view).navigate(action)
    }
}