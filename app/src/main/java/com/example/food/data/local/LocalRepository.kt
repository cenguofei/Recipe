package com.example.food.data.local

import android.content.Context
import com.example.food.data.local.entity.FavoriteEntity
import com.example.food.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

class LocalRepository(context: Context) {
    private val recipeDao = RecipeDatabase.getInstance(context).getRecipeDao()
    //插入数据
    suspend fun insertRecipe(recipeEntity: RecipeEntity){
        recipeDao.insertRecipe(recipeEntity)
    }

    //查询数据
    fun getRecipes(type:String): Flow<List<RecipeEntity>>{
        return recipeDao.getRecipes(type)
    }

    //更新数据
    suspend fun updateRecipe(recipeEntity: RecipeEntity){
        recipeDao.updateRecipe(recipeEntity)
    }


    fun getAllFavorites():Flow<List<FavoriteEntity>>{
        return recipeDao.getAllFavorites()
    }

    suspend fun insertFavorite(favoriteEntity: FavoriteEntity){
        recipeDao.insertFavorite(favoriteEntity)
    }

    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity){
        recipeDao.deleteFavorite(favoriteEntity)
    }
}