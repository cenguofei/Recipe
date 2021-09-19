package com.example.food.viewModuel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.*
import com.example.food.data.local.LocalRepository
import com.example.food.data.local.entity.RecipeEntity
import com.example.food.data.model.FoodRecipe
import com.example.food.data.remote.RemoteRepository
import com.example.food.util.NetworkResult
import com.example.food.util.showToast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(application: Application,private val state:SavedStateHandle):AndroidViewModel(application) {
    //外部观察
    var recipes:MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()
    //网络请求对象
    private val remoteRepository = RemoteRepository()
    //记录获取数据时的状态  loading   success   error
    //数据库操作对象
    private val localRepository = LocalRepository(getApplication())
    //在recipeFragment页面中切换recipe类型时再返回不从网络读取数据
    private var recipesMap = mutableMapOf<String,NetworkResult.Success<FoodRecipe>>()
    private var singleExistRecipe:NetworkResult.Success<FoodRecipe>? = null
    //外部通过这个方法发起网络请求
    fun fetchFoodRecipes(type:String){
            /**
             * 判断网络状态  是否有网络连接
             */
            val iterator = recipesMap.iterator()
            iterator.forEach {
                if (it.key == type) {
                    singleExistRecipe = it.value
                }
            }
            if (singleExistRecipe != null) {
                Log.v("ttt", "有数据")
            }
            //处于loading状态
            recipes.value = NetworkResult.Loading()
            if (singleExistRecipe != null) {
                recipes.value = singleExistRecipe!!
                Log.v("www", "existed recipe")
            } else {
                if (hasInternetConnection()) {
                    Log.v("www", "internet recipe")
                    //先读取数据库的数据
                    viewModelScope.launch {
                        try {
                            val response = remoteRepository.fetchFoodRecipes(type)
                            if (response.isSuccessful) {
                                //获取数据成功  处于success状态
                                recipes.value = NetworkResult.Success(response.body()!!)

                                // recipesMap.put(type,NetworkResult.Success(response.body()) as NetworkResult.Success<FoodRecipe>)
                                recipesMap.put(
                                    type,
                                    NetworkResult.Success(response.body()) as NetworkResult.Success<FoodRecipe>
                                )

                                //需要将数据保存到数据库
                                localRepository.insertRecipe(
                                    RecipeEntity(
                                        0,
                                        type,
                                        response.body()!!
                                    )
                                )
                            } else {
                                //获取数据失败   处于error状态
                                recipes.value = NetworkResult.Error(response.message())
                            }
                        } catch (e: Exception) {
                            recipes.value = NetworkResult.Error("time out:${e.message}")
                        }
                    }
                } else {
                    Log.v("www", "local recipe")
                    //没有网络
                    showToast(getApplication(), "No Internet Connection")

                    //从数据库读取数据
                    viewModelScope.launch {
                        val result = localRepository.getRecipes(type)
                        result.collect {
                            if (it.isNotEmpty()) {
                                val entity = it.first()
                                val data = entity.recipe
                                recipes.value = NetworkResult.Success(data)

                                recipesMap.put(type, NetworkResult.Success(data))
                                //existedRecipes = NetworkResult.Success(data)
                            } else {
                                recipes.value = NetworkResult.Error("empty")
                            }
                        }
                    }
                }
            }
            /*//处于loading状态
        recipes.value = NetworkResult.Loading()
        if(hasInternetConnection()) {
            Log.v("jjj","hasInternetConnection")
            //先读取数据库的数据
            viewModelScope.launch {
                try {
                    val response = remoteRepository.fetchFoodRecipes(type)
                    if (response.isSuccessful) {
                        //获取数据成功  处于success状态
                        recipes.value = NetworkResult.Success(response.body()!!)

                        //需要将数据保存到数据库
                        localRepository.insertRecipe(RecipeEntity(0,type,response.body()!!))
                    }else{
                        //获取数据失败   处于error状态
                        recipes.value = NetworkResult.Error(response.message())
                    }
                }catch (e:Exception){
                    recipes.value = NetworkResult.Error("time out:${e.message}")
                }
            }
        }else{
            Log.v("jjj","noInternetConnection")
            //没有网络
           showToast(getApplication(),"No Internet Connection")

            //从数据库读取数据
            viewModelScope.launch {
                val result = localRepository.getRecipes(type)
                result.collect {
                    if (it.isNotEmpty()) {
                        val entity = it.first()
                        val data = entity.recipe
                        recipes.value = NetworkResult.Success(data)
                    }else{
                        recipes.value = NetworkResult.Error("empty")
                    }
                }
            }
        }*/
            singleExistRecipe = null
    }
    //判断是否有网络连接
    private fun hasInternetConnection():Boolean{
        //获取系统的网络连接管理器
        val connectivityManager = getApplication<Application>()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetWork = connectivityManager.activeNetwork ?: return false

        val capability = connectivityManager
            .getNetworkCapabilities(activeNetWork)?:return false
        return when{
            capability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)-> true
            capability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)-> true
            capability.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)-> true
            else -> false
        }
    }
}