package com.example.markers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.markers.R
import com.example.markers.databinding.DialogLoaderBinding

class DialogLoader(
    var title: String
): DialogFragment() {

    companion object{
        val TAG: String = this::class.java.name
    }

    lateinit var binding: DialogLoaderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initializeBinding(inflater)
        initializeViews()
        return binding.root
    }

    fun initializeViews(){
        binding.textviewDialogLoader.text = title
    }

    fun initializeBinding(
        inflater: LayoutInflater,
    ){
        binding = DataBindingUtil.inflate(
            inflater, R.layout.base_dialog_loader, null, false
        )
    }
}