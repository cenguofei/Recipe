package com.example.food.fragment.recipe.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food.databinding.ItemTypeBinding

class TypeAdapter:RecyclerView.Adapter<TypeAdapter.MyViewHolder>() {
    //事件回调的callBack
    var callBack:((current:Int,last:Int)->Unit)? = null
    val typeList = listOf("main course","side dish", "dessert","appetizer",
        "salad","bread", "breakfast", "soup", "beverage", "sauce", "marinade",
        "fingerfood", "snack", "drink")
    private var lastSelectedPosition = 0
    class MyViewHolder(private val binding:ItemTypeBinding):RecyclerView.ViewHolder(binding.root){
        //回调数据
        var callBack:((Int)->Unit)? = null
        companion object{
            //创建ViewHolder
            fun from(parent: ViewGroup):MyViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                return MyViewHolder(ItemTypeBinding.inflate(inflater))
            }
        }
        //绑定数据
        fun bind(type:String,position: Int){
            binding.titleTextView.text = type
            binding.titleTextView.setOnClickListener {
                callBack?.let {
                    it(position)
                }
            }
        }
        fun changeSelectedStatus(status:Boolean){
            binding.titleTextView.isSelected = status
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.v("cgf","onCreateViewHolder")
        val holder = MyViewHolder.from(parent)
        //处理点击之后的回调事件
        holder.callBack = {
            if (it != lastSelectedPosition){
                callBack?.let { call->
                    call(it,lastSelectedPosition)
                    //记录当前被选中的索引
                    lastSelectedPosition = it
                }
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.v("cgf","onBindViewHolder")
        holder.bind(typeList[position],position)
        //默认第一个为选中状态
        if (position == lastSelectedPosition){
            holder.changeSelectedStatus(true)
        }else{
            holder.changeSelectedStatus(false)
        }
    }

    override fun getItemCount(): Int {
        return typeList.size
    }
}