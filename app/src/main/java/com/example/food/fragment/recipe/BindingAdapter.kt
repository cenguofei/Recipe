package com.example.food.fragment.recipe

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.food.R
import com.google.android.material.chip.Chip


object BindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageWithUrl")
    fun loadImageWithUrl(imageView: ImageView,url:String){
        //将url对应的图片下载下来  显示导ImageView上
        //Glide 三方库   可以完成图片的下载
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("loadIngredientImageWithUrl")
    fun loadIngredientImageWithUrl(imageView: ImageView,name:String){
        //将url对应的图片下载下来  显示导ImageView上
        //Glide 三方库   可以完成图片的下载
        val imageBaseUrl = "https://spoonacular.com/cdn/ingredients_250x250/"
        Glide.with(imageView.context)
            .load(imageBaseUrl+name)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("changeStatus")
    fun changeStatus(chip:Chip,status:Boolean){
        chip.isSelected = status
    }

    /*@JvmStatic
    @BindingAdapter("navigateToDetail")
    fun navigateToDetail(view:View,result:Result){
        view.findNavController().navigate(R.id.action_recipeFragment_to_detailFragment)
    }*/
}