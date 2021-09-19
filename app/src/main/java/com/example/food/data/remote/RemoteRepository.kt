package com.example.food.data.remote

import com.example.food.data.model.FoodRecipe
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepository {
    //创建foodApi对象
    private val foodApi:FoodApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(FoodApi::class.java)
    }
    //给外部提供访问接口
    suspend fun fetchFoodRecipes(type:String): Response<FoodRecipe>{
        return foodApi.fetchFoodRecipes(type)
    }
}