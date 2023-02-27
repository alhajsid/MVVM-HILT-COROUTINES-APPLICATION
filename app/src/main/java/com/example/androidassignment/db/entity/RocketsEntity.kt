package com.example.androidassignment.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidassignment.model.RocketsModel

@Entity(tableName = "ROCKETS")
data class RocketsEntity(
    @PrimaryKey var id: String,
    val name: String?,
    val engines: EnginesEntity?,
    val flickr_images: List<String>?,
    val country: String?,
    val active: Boolean?,
    val cost_per_launch: Int?,
    val description: String?,
    val diameter: DiameterEntity,
    val height: HeightEntity,
    val success_rate_pct: Int?,
    val wikipedia: String?
)