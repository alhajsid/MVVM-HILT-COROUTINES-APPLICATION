package com.example.androidassignment.di

import android.app.Application
import com.example.androidassignment.api.SpaceXApi
import com.example.androidassignment.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesSpaceXApi(retrofit: Retrofit): SpaceXApi {
        return retrofit.create(SpaceXApi::class.java)
    }


}