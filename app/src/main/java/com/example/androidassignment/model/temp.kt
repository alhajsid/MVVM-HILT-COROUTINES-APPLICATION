package com.example.androidassignment.model

class temp : ArrayList<temp.tempItem>(){
    data class tempItem(
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
    ) {
        data class Diameter(
            val feet: Double
        )

        data class Engines(
            val number: Int
        )

        data class Height(
            val feet: Double
        )
    }
}