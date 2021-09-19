package com.example.food.data.remote

import com.example.food.data.model.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    //获取所有的数据  食谱
    //https://api.spoonacular.com/recipes/complexSearch?addRecipeInformation=true&fillIngredients=true&apiKey=59be821b953345298216add8865d7790&number=50   基地址
    @GET("recipes/complexSearch?addRecipeInformation=true&fillIngredients=true&apiKey=59be821b953345298216add8865d7790&number=50")
    suspend fun fetchFoodRecipes(@Query("type")type:String):Response<FoodRecipe>
}