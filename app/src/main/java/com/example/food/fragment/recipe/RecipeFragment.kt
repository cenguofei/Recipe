package com.example.food.fragment.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food.databinding.FragmentRecipeBinding
import com.example.food.fragment.recipe.adapter.FoodAdapter
import com.example.food.fragment.recipe.adapter.TypeAdapter
import com.example.food.util.NetworkResult
import com.example.food.util.showToast
import com.example.food.viewModuel.MainViewModel

class RecipeFragment : Fragment() {
    private lateinit var binding: FragmentRecipeBinding
    private val typeAdapter = TypeAdapter()
    private val foodAdapter = FoodAdapter()
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRecipeBinding.inflate(inflater)
        //初始化recyclerView
        initTypeRecyclerView()
        initFoodRecyclerView()
        //观察数据
        mainViewModel.recipes.observe(viewLifecycleOwner){
            /*if (it.results.isNotEmpty()) {
                //显示数据
                //结束shimmer的加载效果
                binding.foodRecyclerView.hideShimmer()
                //传递下载的数据
                foodAdapter.setData(it.results)
            }*/
            when(it){
                is NetworkResult.Success -> {
                    binding.foodRecyclerView.hideShimmer()
                    foodAdapter.setData(it.data!!.results)
                }
                is NetworkResult.Loading ->{
                    binding.foodRecyclerView.showShimmer()
                }
                is NetworkResult.Error ->{
                    Log.v("jjj","次数上限")
                    binding.foodRecyclerView.hideShimmer()
                    showToast(requireContext(),it.message!!)
                }
            }
        }
        //默认显示main course的数据
        fetchData("main course")
        //mainViewModel.getRecipeFromLocal("main course")
        return binding.root
    }


    //获取数据
    private fun fetchData(type:String){
        mainViewModel.fetchFoodRecipes(type)
    }
    //配置类型选择得recyclerView
    private fun initTypeRecyclerView(){
        binding.typeRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),RecyclerView.HORIZONTAL,false
        )
        binding.typeRecyclerView.adapter = typeAdapter
        //处理回调事件
        typeAdapter.callBack = {current,last->
            val currentHolder = binding.typeRecyclerView.findViewHolderForAdapterPosition(current) as TypeAdapter.MyViewHolder
            val lastHolder = binding.typeRecyclerView.findViewHolderForAdapterPosition(last)
            //上一次的ViewHolder为空  需要进行处理
            if(lastHolder != null){
                Log.v("cgf","not null")
                val lastTypeHolder = lastHolder as TypeAdapter.MyViewHolder
                //取消原先选中的
                lastTypeHolder.changeSelectedStatus(false)
            }else{
                Log.v("cgf","null")
                //通知刷新
                typeAdapter.notifyItemChanged(last)
            }
            //获取数据
            fetchData(typeAdapter.typeList[current])
            currentHolder.changeSelectedStatus(true)
        }
    }
    private fun initFoodRecyclerView(){
        binding.foodRecyclerView.layoutManager = GridLayoutManager(
            requireContext(),2
        )
        binding.foodRecyclerView.adapter = foodAdapter
    }

    override fun onDestroy() {
        Log.v("rrr","recipeFragment:onDestroy")
        super.onDestroy()
    }
}