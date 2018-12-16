package com.noox.postschallenge.common.di

import com.noox.postschallenge.common.data.ApiService
import com.noox.postschallenge.common.extensions.addLogInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val API_SERVER_URL = "https://jsonplaceholder.typicode.com/"

val appModule = module {

    single { createApiManager(createOkHttpClient(), API_SERVER_URL) }

}

private fun createApiManager(client: OkHttpClient, url: String): ApiService {

    return Retrofit.Builder()
        .client(client)
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<ApiService>(ApiService::class.java)
}

private fun createOkHttpClient(): OkHttpClient {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addLogInterceptor()
        .build()
}