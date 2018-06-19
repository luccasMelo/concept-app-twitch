package com.luccasmelo.concept.di.module

import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.luccasmelo.concept.BuildConfig
import com.luccasmelo.concept.data.ApiService
import com.luccasmelo.concept.data.repository.GamesRepository
import com.luccasmelo.concept.di.PerActivity
import com.luccasmelo.concept.utils.Constants
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
class ActivityModule(private val activity: AppCompatActivity) {




    @PerActivity
    @Provides
    fun providesActivity(): AppCompatActivity = activity

    @PerActivity
    @Provides
    fun providesLayoutInflater(activity: AppCompatActivity): LayoutInflater =
            LayoutInflater.from(activity)

    @PerActivity
    @Provides
    fun providesFragmentManager(activity: AppCompatActivity): android.support.v4.app.FragmentManager =
            activity.supportFragmentManager



    @PerActivity
    @Provides
    fun provideGamesRepository(@Named("apiService")apiService: ApiService): GamesRepository = GamesRepository()


}