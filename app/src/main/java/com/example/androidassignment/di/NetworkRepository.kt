package com.example.androidassignment.di

import com.example.androidassignment.api.SpaceXApi
import com.example.androidassignment.model.RocketModel
import com.example.androidassignment.model.RocketsModel
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val spaceXApi: SpaceXApi
) {

    suspend fun getAllRockets(): Response<RocketsModel> {
        return spaceXApi.getAllRockets()
    }
    suspend fun getRocket(id:String): Response<RocketModel> {
        return spaceXApi.getRocket(id)
    }

}