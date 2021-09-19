package com.example.food.fragment.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.food.databinding.FragmentSummaryBinding
import org.jsoup.Jsoup

class SummaryFragment(val summary:String): Fragment() {

    private lateinit var binding:FragmentSummaryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSummaryBinding.inflate(inflater)
        //summary包含html  用Jsoup转换
        //要用text
        binding.summaryTextView.text = Jsoup.parse(summary).text()
        return  binding.root
    }
}