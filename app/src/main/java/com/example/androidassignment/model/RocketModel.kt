package com.example.androidassignment.model

data class RocketModel(
    val active: Boolean,
    val cost_per_launch: Int,
    val description: String,
    val diameter: Diameter,
    val flickr_images: List<String>,
    val height: Height,
    val id: String,
    val name: String,
    val success_rate_pct: Int,
    val wikipedia: String
) {
    data class Diameter(
        val feet: Double
    )

    data class Height(
        val feet: Double
    )

}