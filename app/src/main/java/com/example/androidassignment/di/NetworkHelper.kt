package com.example.androidassignment.di

import android.content.Context
import com.example.androidassignment.utils.NetworkConnectivityHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkHelper {

    @Singleton
    @Provides
    fun providesNetworkConnectivityHelper(@ApplicationContext context: Context): NetworkConnectivityHelper {
        return NetworkConnectivityHelper(context)
    }
}