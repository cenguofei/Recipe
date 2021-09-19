package com.example.food.fragment.recipe.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food.data.model.ExtendedIngredient
import com.example.food.databinding.IngredientItemBinding


class IngredientAdapter: RecyclerView.Adapter<IngredientAdapter.MyViewHolder>() {
    private var ingredientList:List<ExtendedIngredient> = emptyList()

    class MyViewHolder(val binding: IngredientItemBinding)
        :RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = IngredientItemBinding.inflate(inflater)
                return MyViewHolder(binding)
            }
        }
        //要写在companion object的外面
        fun bind(ingredient:ExtendedIngredient){
            binding.ingredient = ingredient
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ingredient = ingredientList[position]
        Log.v("pl","配料url:${ingredient.image}")
        holder.bind(ingredientList[position])
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    fun setData(newData:List<ExtendedIngredient>){
        ingredientList = newData
        notifyDataSetChanged()
    }
}