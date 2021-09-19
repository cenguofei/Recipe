package com.example.food.data.local

import androidx.room.*
import com.example.food.data.local.entity.FavoriteEntity
import com.example.food.data.local.entity.RecipeEntity
import com.example.food.data.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    //插入数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeEntity: RecipeEntity)

    //查询数据
    @Query("select * from foodRecipeTable where type=:type")
    fun getRecipes(type:String):Flow<List<RecipeEntity>>

    //更新数据
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRecipe(recipeEntity: RecipeEntity)

    /**    favorite   收藏的食谱    */
    @Query("select * from favorite_table")
    fun getAllFavorites():Flow<List<FavoriteEntity>>

    @Insert
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)
}