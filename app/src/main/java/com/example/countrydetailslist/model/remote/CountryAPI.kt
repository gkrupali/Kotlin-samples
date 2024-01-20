package com.example.countrydetailslist.model.remote

import com.example.countrydetailslist.model.CountryItem
import retrofit2.Response
import retrofit2.http.GET


/**
 * Interface to define network apis
 */
interface CountryAPI {

    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/") // hardcoded as of now
    suspend fun getCountryDetails(): Response<List<CountryItem>>
}
