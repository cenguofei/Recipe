package com.example.food.util


sealed class NetworkResult<T>(
    val data:T? = null,
    //错误信息
    val message:String? = null
){
    class Error<T>(errMsg:String):NetworkResult<T>(message = errMsg)
    /**正在请求*/
    class Loading<T>():NetworkResult<T>()
    /**请求成功*/
    class Success<T>(data:T):NetworkResult<T>(data)
}
