package com.example.clustering.application

import android.app.Application
import com.example.base.di.BaseModule
import com.example.markers.di.MarkersModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ClusteringApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    fun initializeKoin(){
        startKoin {
            androidContext(this@ClusteringApplication)
            modules(
                BaseModule,
                MarkersModule
            )
        }
    }
}