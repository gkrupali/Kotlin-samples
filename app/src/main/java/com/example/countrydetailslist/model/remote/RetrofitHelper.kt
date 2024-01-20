package com.example.countrydetailslist.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    private const val BASE_URL = "https://gist.githubusercontent.com/"

    fun getRetrofitInstance() : CountryAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CountryAPI::class.java)
    }

}