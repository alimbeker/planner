package com.example.plannerproject.api

import com.example.plannerproject.database.CardEntity
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.http.GET
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

//Requests
private const val BASE_URL ="http://127.0.1.1:5000/api/"

private val moshi  = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService{
    @GET("all")
    suspend fun getProperties():
            List<CardEntity>
    @POST("add")
    suspend fun addTask(@Body card:CardEntity)

    @DELETE("deleteAll")
    suspend fun deleteAll();

//    @DELETE("delete/{id}")
//    suspend fun deleteById(@Path("id")id:Int);

    object MarsApi{
        val retrofitService:MarsApiService by lazy {
            retrofit.create(MarsApiService::class.java)
        }
    }

}