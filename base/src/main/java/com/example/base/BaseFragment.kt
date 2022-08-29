package com.example.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: ViewDataBinding>: Fragment() {
    lateinit var binding: T

    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initializeBinding(inflater, initializeLayout(), container)
        rootView = binding.root
        return rootView
    }

    abstract fun initializeLayout(): Int

    fun initializeBinding(layoutInflater: LayoutInflater, layoutId: Int, viewGroup: ViewGroup?){
        binding = DataBindingUtil
            .inflate(layoutInflater, layoutId, viewGroup, false)
    }
}