package com.example.countrydetailslist.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.countrydetailslist.model.remote.CountryAPI
import kotlinx.coroutines.delay


class CountryRepository(private val countryAPI: CountryAPI) {

    private val _currentCountryList = MutableLiveData<UiState>()
    val currentCountryList:LiveData<UiState> get() = _currentCountryList


    suspend fun getCountryList(){
        _currentCountryList.postValue(UiState.Loading)
        delay(500) // Simulating loading state
        val response = countryAPI.getCountryDetails()
        if(response.body() != null && response.isSuccessful){
          _currentCountryList.postValue(UiState.Success(response.body()!!))
        }else{
            _currentCountryList.postValue(UiState.Error("Something went wrong!!"))
        }
    }
}