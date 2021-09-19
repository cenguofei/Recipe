package com.example.food.fragment.other

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.food.R
import com.example.food.databinding.FragmentOtherBinding

class OtherFragment : Fragment() {
    private lateinit var binding:FragmentOtherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtherBinding.inflate(inflater)
        Log.v("test","onCreateView")
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("test","onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v("test","onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.v("test","onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.v("test","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v("test","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v("test","onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.v("test","onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.v("test","onDestroyView")
    }

    override fun onDestroy() {
        Log.v("rrr","otherOnDestroy")
        super.onDestroy()
    }
}