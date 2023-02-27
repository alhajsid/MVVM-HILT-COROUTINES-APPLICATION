package com.example.androidassignment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidassignment.db.dao.RocketsDao
import com.example.androidassignment.db.entity.RocketsEntity

@Database(
    version = 2,
    entities = [RocketsEntity::class],
)
@TypeConverters(ConverterEngine::class)
abstract class AppDatabase :RoomDatabase(){
    abstract fun rocketsDao(): RocketsDao
}