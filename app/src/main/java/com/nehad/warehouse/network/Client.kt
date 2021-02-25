package com.nehad.warehouse.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    companion object{
        val Base_URL = "https://whm.signaturegypt.com/api/sync/synctophone/"
        fun getRetrofitInstance(): Retrofit {
            return  Retrofit.Builder().baseUrl(Base_URL).
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
        }
    }
}