package com.example.food.fragment.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food.data.model.Result
import com.example.food.databinding.FavoriteItemBinding


class FavoriteAdapter:RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {
    private var recipeList:List<Result> = emptyList()
    class MyViewHolder(val binding:FavoriteItemBinding):RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent:ViewGroup): MyViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = FavoriteItemBinding.inflate(inflater,parent,false)
                return MyViewHolder(binding)
            }
        }
        fun bind(result:Result){
            binding.recipe = result
            binding.executePendingBindings()
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