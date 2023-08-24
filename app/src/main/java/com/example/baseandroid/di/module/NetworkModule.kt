package com.example.baseandroid.di.module

import com.example.baseandroid.data.repository.IApiRepository
import com.example.baseandroid.utils.Constant
import com.example.baseandroid.utils.network.NetworkInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")//https://api.drcarbs.com/
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): IApiRepository {
        return retrofit.create(IApiRepository::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpClient(tokenInterceptor: NetworkInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(tokenInterceptor)
            .connectTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()

    }





}