package com.example.food.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.food.data.model.FoodRecipe


@Entity(tableName = "foodRecipeTable")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val type:String,
    val recipe:FoodRecipe
)