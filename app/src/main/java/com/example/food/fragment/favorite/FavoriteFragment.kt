package com.example.food.fragment.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food.data.model.Result
import com.example.food.databinding.FragmentFavoriteBinding
import com.example.food.viewModel.FavoriteViewModel

class FavoriteFragment : Fragment() {
    private val favoriteAdapter = FavoriteAdapter()
    private val favoriteViewModel:FavoriteViewModel by viewModels()
    private lateinit var binding:FragmentFavoriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater)
        binding.recyclerView.adapter = favoriteAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        favoriteViewModel.readFavorites()
        favoriteViewModel.favoriteRecipes.observe(viewLifecycleOwner){
            val resultList = mutableListOf<Result>()
            it.forEach {entity->
                resultList.add(entity.result)
            }
            favoriteAdapter.setData(resultList)
        }
        return binding.root
    }
}