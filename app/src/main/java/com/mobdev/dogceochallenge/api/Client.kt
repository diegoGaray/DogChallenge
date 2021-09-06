package com.mobdev.dogceochallenge.api

import com.mobdev.dogceochallenge.BuildConfig
import com.mobdev.dogceochallenge.utils.SharedParameters
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Client {

    internal object RetrofitClientInstance {
        private var retrofit: Retrofit? = null

        val retrofitInstance: Retrofit?

            get() {
                val levelType: HttpLoggingInterceptor.Level = if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
                    HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

                val logging = HttpLoggingInterceptor()
                logging.setLevel(levelType)

                val okhttpClient = OkHttpClient.Builder()
                okhttpClient.addInterceptor(logging)
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(SharedParameters.BASE_URL)
                        .client(okhttpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit
            }
    }

}