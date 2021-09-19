package com.example.food.fragment.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.food.databinding.FragmentDetailBinding
import com.example.food.viewModel.FavoriteViewModel

class DetailFragment : Fragment() {
    private val favoriteViewModel:FavoriteViewModel by viewModels()
    private lateinit var binding:FragmentDetailBinding
    private val recipeArgs:DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        binding.detailBtn.isSelected = true


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recipe = recipeArgs.recipe
        binding.executePendingBindings()
        initEvent()
        initViewPager()
        favoriteViewModel.readFavorites()
        favoriteViewModel.favoriteRecipes.observe(viewLifecycleOwner){
            it.forEach {entity->
                if (entity.result == recipeArgs.recipe){
                    binding.collectBtn.isSelected = true
                    return@forEach
                }
            }
        }
    }

    private fun initViewPager(){
        val fragments = listOf(
            SummaryFragment(recipeArgs.recipe.summary),
            IngredientFragment(recipeArgs.recipe.extendedIngredients)
        )
        binding.viewPager.adapter = ViewPagerAdapter(fragments,requireActivity().supportFragmentManager,lifecycle)
    }
    private fun initEvent(){
        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.detailBtn.setOnClickListener {
            selectDetail()
            binding.viewPager.currentItem = 0
        }

        binding.ingredientsBtn.setOnClickListener {
            selectIngredient()
            binding.viewPager.currentItem = 1
        }
        binding.viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            //关联  当选中viewPager改变时  上方的条也跟着滚动
            override fun onPageSelected(position: Int) {
                if (position == 0){
                    selectDetail()
                }else{
                    selectIngredient()
                }
            }
        })

        binding.collectBtn.setOnClickListener {
            if (it.isSelected){
                //从数据库删除这个食谱
                favoriteViewModel.favoriteRecipes.value?.forEach {entity->
                    if (entity.result == recipeArgs.recipe){
                        favoriteViewModel.deleteFavorite(entity)
                        binding.collectBtn.isSelected = false
                    }
                }
            }else{
                //将这个食谱插入到收藏表中
                favoriteViewModel.insertFavorite(recipeArgs.recipe)
                binding.collectBtn.isSelected = true
            }
           //it.isSelected = !it.isSelected
        }
    }
    private fun selectIngredient(){
        if (!binding.ingredientsBtn.isSelected) {
            binding.ingredientsBtn.isSelected = true
            binding.detailBtn.isSelected = false
            indicatorAnim(binding.ingredientsBtn.x - binding.detailBtn.x)
        }
    }
    private fun selectDetail(){
        if (!binding.detailBtn.isSelected) {
            binding.detailBtn.isSelected = true
            binding.ingredientsBtn.isSelected = false
            indicatorAnim(0f)
        }
    }
    private fun indicatorAnim(value:Float){
        binding.indicatorView.animate()
            .translationX(value)
            .setDuration(300)
            .start()
    }
}