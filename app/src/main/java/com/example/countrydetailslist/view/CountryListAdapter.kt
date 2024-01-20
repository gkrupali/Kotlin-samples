package com.example.countrydetailslist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countrydetailslist.R
import com.example.countrydetailslist.model.CountryItem

class CountryListAdapter : ListAdapter<CountryItem,CountryListAdapter.CountryViewHolder>(DiffListItem()) {


    class CountryViewHolder(view :View):ViewHolder(view){

        private val txtName = view.findViewById<TextView>(R.id.name)
        private val txtRegion = view.findViewById<TextView>(R.id.region)
        private val txtCode = view.findViewById<TextView>(R.id.code)
        private val txtCapital = view.findViewById<TextView>(R.id.capital)

        fun onBind(country:CountryItem){
            txtName.text = country.name
            txtCode.text = country.code
            txtRegion.text = country.region
            txtCapital.text = country.capital
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    class DiffListItem : DiffUtil.ItemCallback<CountryItem>() {

        override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
            return oldItem == newItem
        }

    }

}