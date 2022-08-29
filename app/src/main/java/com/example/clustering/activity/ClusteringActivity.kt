package com.example.clustering.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.clustering.R
import com.example.clustering.databinding.ClusterActivityBinding

class ClusteringActivity: AppCompatActivity() {

    lateinit var clusteringActivityBinding: ClusterActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
    }

    fun initializeBinding(){
        clusteringActivityBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_clustering)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}