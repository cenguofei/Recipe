package com.example.food.fragment.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.food.data.model.Result
import com.example.food.databinding.FoodItemBinding
import com.example.food.fragment.recipe.RecipeFragmentDirections

class FoodAdapter:RecyclerView.Adapter<FoodAdapter.MyViewHolder>() {
    private var recipeList:List<Result> = emptyList()
    class MyViewHolder(private val binding: FoodItemBinding):RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = FoodItemBinding.inflate(inflater)
                return MyViewHolder(binding)
            }
        }
        fun bind(result: Result){
            binding.result = result
            binding.executePendingBindings()
            binding.foodContainer.setOnClickListener {
                val action = RecipeFragmentDirections.actionRecipeFragmentToDetailFragment(result)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    fun setData(newData:List<Result>){
        recipeList = newData
        notifyDataSetChanged()
    }
}