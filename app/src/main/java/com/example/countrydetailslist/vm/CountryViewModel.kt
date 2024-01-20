package com.example.countrydetailslist.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrydetailslist.model.CountryRepository
import com.example.countrydetailslist.model.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class CountryViewModel(private val countryRepository: CountryRepository) :ViewModel(){

     val countryListAsLiveData : LiveData<UiState>  = countryRepository.currentCountryList
     private var _errorStatus = MutableLiveData<UiState>()
     val errorStatus:LiveData<UiState>  get() = _errorStatus

    init {
            val exceptionHandler = CoroutineExceptionHandler { _, exception ->
                _errorStatus.value = UiState.Error("Network Request failed!! $exception")
            }
            viewModelScope.launch(exceptionHandler){
                    countryRepository.getCountryList()
            }
        }
}