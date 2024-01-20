package com.example.countrydetailslist.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countrydetailslist.model.CountryRepository

class ViewModelFactory(private val countryRepository: CountryRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CountryRepository::class.java).newInstance(countryRepository)
    }
}