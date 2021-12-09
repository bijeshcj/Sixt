package com.bijesh.application.network

import com.bijesh.application.models.CarsItem
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SixtWebService {

    @GET("cars")
    fun getCars() : Call<List<CarsItem>>



    companion object{
        var retrofitService: SixtWebService? = null

        fun getInstance(): SixtWebService{
            var httpClient : OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder().build()
                    return chain.proceed(request)
                }

            })
            if (retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://cdn.sixt.io/codingtask/").client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(SixtWebService::class.java)
            }
            return retrofitService!!
        }

    }

}