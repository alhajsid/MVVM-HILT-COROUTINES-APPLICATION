package com.example.androidassignment.api

import com.example.androidassignment.model.RocketModel
import com.example.androidassignment.model.RocketsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXApi {
    @GET("v4/rockets")
    suspend fun getAllRockets(
    ): Response<RocketsModel>

    @GET("v4/rockets/{id}")
    suspend fun getRocket(
        @Path("id") id:String
    ): Response<RocketModel>

}