package com.luccasmelo.concept.di.module

import com.luccasmelo.concept.BuildConfig
import com.luccasmelo.concept.utils.Constants
import com.luccasmelo.concept.data.ApiService
import com.luccasmelo.concept.di.PerActivity
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton



@Module
class ApiModule {

    @Singleton
    @Provides
    @Named("endPoint")
    fun endPoint() = Constants.ENDPOINT

    @Singleton
    @Provides
    fun provideOkHttpBuilder(): OkHttpClient.Builder {
        val okHttpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            okHttpBuilder.addInterceptor(logging)
        }
        return okHttpBuilder.apply {
            readTimeout(15.toLong(), TimeUnit.SECONDS)
            connectTimeout(15.toLong(), TimeUnit.SECONDS)
        }
    }

    @Singleton
    @Provides
    @Named("apiServiceRetrofit")
    fun provideApiServiceRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    @Named("apiService")
    fun provideApiService(@Named("apiServiceRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()


}