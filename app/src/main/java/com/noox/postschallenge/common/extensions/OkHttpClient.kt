package com.noox.postschallenge.common.extensions

import android.util.Log
import com.noox.postschallenge.BuildConfig
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import java.util.*

fun OkHttpClient.Builder.addLogInterceptor() : OkHttpClient.Builder {

    if (BuildConfig.DEBUG) {
        addInterceptor { chain ->

            val builder1 = chain.request().newBuilder()

            val request = builder1.build()

            val startTime = System.nanoTime()
            Log.i(
                "OkHttpClient",
                String.format("Sending request: %s\n%s", request.url(), request.headers())
            )

            val response = chain.proceed(request)

            val requestTime = (System.nanoTime() - startTime) / 1e6
            Log.i(
                "OkHttpClient", String.format(
                    Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), requestTime, response.headers()
                )
            )

            Log.i("OkHttpClient", "responseCode: " + response.code())

            val body = response.body()
            if (body != null) {

                val responseBody = body.string()
                Log.i("OkHttpClient", "responseBody: $responseBody")

                response.newBuilder()
                    .body(ResponseBody.create(body.contentType(), responseBody))
                    .build()

            } else {

                Log.i("OkHttpClient", "responseBody: NULL")
                response.newBuilder()
                    .body(ResponseBody.create(MediaType.parse("txt"), ""))
                    .build()
            }
        }
    }
    return this
}