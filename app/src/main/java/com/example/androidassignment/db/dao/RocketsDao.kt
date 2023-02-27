package com.example.androidassignment.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidassignment.db.entity.RocketsEntity

@Dao
interface RocketsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rocketEntity: RocketsEntity):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rockets: List<RocketsEntity>)

    @Query("SELECT * FROM ROCKETS")
    suspend fun getAllOfflineRockets(): List<RocketsEntity>

    @Query("SELECT * FROM ROCKETS WHERE id = :mid")
    suspend fun getOfflineRocket(mid:String): RocketsEntity?

    @Delete
    suspend fun delete(rocketEntity: RocketsEntity)
}