package com.example.food.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.food.data.local.LocalRepository
import com.example.food.data.local.entity.FavoriteEntity
import com.example.food.data.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application):AndroidViewModel(application) {
    private val localRepository = LocalRepository(application)
    val favoriteRecipes:MutableLiveData<List<FavoriteEntity>> = MutableLiveData()


    fun readFavorites(){
        viewModelScope.launch {
            localRepository.getAllFavorites().collect {
                favoriteRecipes.value = it
            }
        }
    }

    fun insertFavorite(result: Result){
        val favoriteEntity = FavoriteEntity(0,result)
        viewModelScope.launch {
            localRepository.insertFavorite(favoriteEntity)
        }
    }

    fun deleteFavorite(favoriteEntity: FavoriteEntity){
        viewModelScope.launch {
            localRepository.deleteFavorite(favoriteEntity)
        }
    }
}