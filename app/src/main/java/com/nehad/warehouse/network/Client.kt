package com.nehad.warehouse.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    companion object{
        //http://whm.signaturegypt.com/api/sync/
       // val Base_URL = "https://whm.signaturegypt.com/api/sync/synctophone/"
        val Base_URL = "/http://whm.signaturegypt.com/api/sync/"

        fun getRetrofitInstance(): Retrofit {
            return  Retrofit.Builder().baseUrl(Base_URL).
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
        }
    }
}