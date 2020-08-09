package com.example.mebank.Activity.Network

import com.example.mebank.Activity.Model.EmployeeInputModel
import com.example.mebank.Activity.Model.EmployeeListModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


private val service: Network by lazy {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://127.0.0.1:5000/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(Network::class.java)
}

fun getNetworkService() = service

interface Network {

    @GET("employees")
    suspend fun fetchEmployeeList():List<EmployeeListModel>

    @GET("employees/id")
    suspend fun fetchEmployeeDetail(@Body id: Int):EmployeeListModel

}