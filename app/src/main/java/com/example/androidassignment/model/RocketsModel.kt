package com.example.androidassignment.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RocketsModel :  ArrayList<RocketsModel.RocketsModelItem>(),Parcelable{

    @Parcelize
    data class RocketsModelItem(
        val active: Boolean,
        val cost_per_launch: Int,
        val country: String,
        val description: String,
        val diameter: Diameter,
        val engines: Engines,
        val flickr_images: List<String>,
        val height: Height,
        val id: String,
        val name: String,
        val success_rate_pct: Int,
        val wikipedia: String
    ):Parcelable {
        @Parcelize
        data class Diameter(
            val feet: Double
        ):Parcelable
        @Parcelize
        data class Engines(
            val number: Int
        ):Parcelable
        @Parcelize
        data class Height(
            val feet: Double
        ):Parcelable
    }
}