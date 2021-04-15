package com.nehad.warehouse.network

import com.nehad.warehouse.model.AllData
import com.nehad.warehouse.model.DocumentHeader
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("synctophone.php")
    suspend fun getAllData(): Response<AllData>


    @GET("synctophone/synctophone.php")
    fun getData(): Call<AllData>
    //http://whm.signaturegypt.com/api/sync/syncfromphone/syncdocuments.php
  //  "https://whm.signaturegypt.com/api/sync/synctophone/"

    @POST("syncfromphone/syncdocuments.php")
    fun postDoc(): Call<DocumentHeader>

//https://johncodeos.com/how-to-make-post-get-put-and-delete-requests-with-retrofit-using-kotlin/
//    @POST("/api/v1/create")
//    suspend fun createEmployee(@Body documentHeader: DocumentHeader): Response<DocumentHeader>
}