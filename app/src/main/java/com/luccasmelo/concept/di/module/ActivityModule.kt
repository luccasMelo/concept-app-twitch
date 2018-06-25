package com.luccasmelo.concept.di.module

import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.luccasmelo.concept.data.repository.ApiService
import com.luccasmelo.concept.data.repository.GamesRepository
import com.luccasmelo.concept.di.PerActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named

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
    fun provideGamesRepository(@Named("apiService")apiService: ApiService): GamesRepository = GamesRepository(apiService)


}