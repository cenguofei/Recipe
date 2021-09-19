package com.example.food.util

import androidx.room.TypeConverter
import com.example.food.data.model.FoodRecipe
import com.example.food.data.model.Result
import com.google.gson.Gson


class RecipeTypeConverter {
    //FoodRecipe->string
    @TypeConverter
    fun foodRecipeToString(recipe:FoodRecipe):String{
        return Gson().toJson(recipe)
    }
    //string->FoodRecipe
    @TypeConverter
    fun stringToFoodRecipe(str:String):FoodRecipe{
        return Gson().fromJson(str,FoodRecipe::class.java)
    }

    //收藏
    @TypeConverter
    fun resultToString(recipe:Result):String{
        return Gson().toJson(recipe)
    }

    @TypeConverter
    fun stringToResult(str: String):Result{
        return Gson().fromJson(str,Result::class.java)
    }
}