package com.example.androidassignment.db

import androidx.room.TypeConverter
import com.example.androidassignment.db.entity.DiameterEntity
import com.example.androidassignment.db.entity.EnginesEntity
import com.example.androidassignment.db.entity.HeightEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterEngine {

    val gson = Gson()

    @TypeConverter
    fun toSourceEntityToString(engineEntity: EnginesEntity): String {
        val type = object : TypeToken<EnginesEntity>() {}.type
        return gson.toJson(engineEntity, type)
    }

    @TypeConverter
    fun fromStringToSourceEntity(string: String): EnginesEntity {
        val type = object : TypeToken<EnginesEntity>() {}.type
        return gson.fromJson(string, type)

    }
    @TypeConverter
    fun toListToString(list: List<String>?): String {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun fromStringToList(string: String): List<String>? {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(string, type)
    }

    @TypeConverter
    fun toHeightEntityToString(engineEntity: HeightEntity): String {
        val type = object : TypeToken<HeightEntity>() {}.type
        return gson.toJson(engineEntity, type)
    }

    @TypeConverter
    fun fromStringToHeightEntity(string: String): HeightEntity {
        val type = object : TypeToken<HeightEntity>() {}.type
        return gson.fromJson(string, type)

    }

    @TypeConverter
    fun toDiameterToString(engineEntity: DiameterEntity): String {
        val type = object : TypeToken<DiameterEntity>() {}.type
        return gson.toJson(engineEntity, type)
    }

    @TypeConverter
    fun fromStringToDiameter(string: String): DiameterEntity {
        val type = object : TypeToken<DiameterEntity>() {}.type
        return gson.fromJson(string, type)

    }
}