package com.example.androidassignment.di

import androidx.lifecycle.LiveData
import com.example.androidassignment.db.AppDatabase
import com.example.androidassignment.db.entity.RocketsEntity
import com.example.androidassignment.di.Transformer.convertRocketModelToRocketEntity
import com.example.androidassignment.model.RocketsModel
import javax.inject.Inject

class DBRepository @Inject constructor(private val appDatabase: AppDatabase) {

    suspend fun insertRocket(rocket: RocketsModel.RocketsModelItem): Long {
        return appDatabase.rocketsDao()
            .insert(convertRocketModelToRocketEntity(rocket))
    }

    suspend fun insertAllRocket(rockets: ArrayList<RocketsModel.RocketsModelItem>) {

        val temp = rockets.map {rocket->
            convertRocketModelToRocketEntity(rocket)
        }
        appDatabase.rocketsDao()
            .insertAll(temp)
    }

    suspend fun delete(rocket: RocketsModel.RocketsModelItem) {
        appDatabase.rocketsDao().delete(convertRocketModelToRocketEntity(rocket))
    }

    suspend fun getAllRockets(): List<RocketsEntity> {
        return appDatabase.rocketsDao().getAllOfflineRockets()
    }
    suspend fun getRocket(id:String): RocketsEntity? {
        return appDatabase.rocketsDao().getOfflineRocket(id)
    }


}