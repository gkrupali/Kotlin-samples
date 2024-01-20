package com.example.countrydetailslist.model


sealed class UiState {
    object Loading:UiState()
    class Success(val countryList: List<CountryItem>) :UiState()
    class Error(val message:String) :UiState()
}