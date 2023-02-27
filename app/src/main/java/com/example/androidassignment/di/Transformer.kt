package com.example.androidassignment.di

import com.example.androidassignment.db.entity.DiameterEntity
import com.example.androidassignment.db.entity.EnginesEntity
import com.example.androidassignment.db.entity.HeightEntity
import com.example.androidassignment.db.entity.RocketsEntity
import com.example.androidassignment.model.RocketModel
import com.example.androidassignment.model.RocketsModel


object Transformer {

    fun convertRocketModelToRocketEntity(rocket: RocketsModel.RocketsModelItem): RocketsEntity {
        return RocketsEntity(
            name = rocket.name,
            country = rocket.country,
            engines = convertEngineModelToEngineEntity(rocket.engines),
            flickr_images = rocket.flickr_images,
            id = rocket.id,
            active = rocket.active,
            cost_per_launch = rocket.cost_per_launch,
            description = rocket.description,
            diameter = convertDiameterModelToDiameterEntity(rocket.diameter) ?: DiameterEntity(0.0),
            height = convertHeightModelToHeightEntity(rocket.height) ?: HeightEntity(0.0),
            success_rate_pct = rocket.success_rate_pct,
            wikipedia = rocket.wikipedia
        )
    }

    fun convertRocketEntityToRocketModel(rocketEntity: RocketsEntity): RocketsModel.RocketsModelItem {
        return RocketsModel.RocketsModelItem(
            name = rocketEntity.name ?: "",
            country = rocketEntity.country ?: "",
            engines = convertEngineEntityToEngineModel(rocketEntity.engines)
                ?: RocketsModel.RocketsModelItem.Engines(0),
            flickr_images = rocketEntity.flickr_images ?: arrayListOf(),
            id = rocketEntity.id,
            active = rocketEntity.active ?: false,
            cost_per_launch = rocketEntity.cost_per_launch ?: 0,
            description = rocketEntity.description ?: "",
            diameter = convertDiameterEntityToDiameterModel(rocketEntity.diameter)?:
            RocketsModel.RocketsModelItem.Diameter(0.0),
            height = convertHeightEntityToHeightModel( rocketEntity.height )?:
            RocketsModel.RocketsModelItem.Height(0.0),
            success_rate_pct = rocketEntity.success_rate_pct ?: 0,
            wikipedia = rocketEntity.wikipedia ?: ""
        )
    }

    fun convertRocketsModelToRocketModel(rocketEntity: RocketsModel.RocketsModelItem): RocketModel {
        return RocketModel(
            name = rocketEntity.name ?: "",
            flickr_images = rocketEntity.flickr_images ?: arrayListOf(),
            id = rocketEntity.id,
            active = rocketEntity.active ?: false,
            cost_per_launch = rocketEntity.cost_per_launch ?: 0,
            description = rocketEntity.description ?: "",
            diameter = RocketModel.Diameter(rocketEntity.diameter.feet),
            height =  RocketModel.Height(rocketEntity.height.feet),
            success_rate_pct = rocketEntity.success_rate_pct ?: 0,
            wikipedia = rocketEntity.wikipedia ?: ""
        )
    }

    private fun convertEngineModelToEngineEntity(engine: RocketsModel.RocketsModelItem.Engines?): EnginesEntity? {
        engine?.let {
            return EnginesEntity(engine.number)
        }
        return null
    }

    private fun convertHeightModelToHeightEntity(engine: RocketsModel.RocketsModelItem.Height?): HeightEntity? {
        engine?.let {
            return HeightEntity(engine.feet)
        }
        return null
    }

    private fun convertDiameterModelToDiameterEntity(engine: RocketsModel.RocketsModelItem.Diameter?): DiameterEntity? {
        engine?.let {
            return DiameterEntity(engine.feet)
        }
        return null
    }

    private fun convertEngineEntityToEngineModel(enginesEntity: EnginesEntity?): RocketsModel.RocketsModelItem.Engines? {
        enginesEntity?.let {
            return RocketsModel.RocketsModelItem.Engines(enginesEntity.number ?: 0)
        }
        return null
    }

    private fun convertHeightEntityToHeightModel(enginesEntity: HeightEntity?): RocketsModel.RocketsModelItem.Height? {
        enginesEntity?.let {
            return RocketsModel.RocketsModelItem.Height(enginesEntity.feet ?: 0.0)
        }
        return null
    }

    private fun convertDiameterEntityToDiameterModel(enginesEntity: DiameterEntity?): RocketsModel.RocketsModelItem.Diameter? {
        enginesEntity?.let {
            return RocketsModel.RocketsModelItem.Diameter(enginesEntity.feet ?: 0.0)
        }
        return null
    }
}