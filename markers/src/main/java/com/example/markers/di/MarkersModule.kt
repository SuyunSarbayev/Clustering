package com.example.markers.di

import com.example.markers.utils.PermissionsUtils
import com.example.markers.viewmodel.ClustersFragmentViewModel
import com.example.markers.utils.Reader
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var MarkersModule = module{

    factory { Reader(get()) }

    single { PermissionsUtils(get()) }

    viewModel {
        ClustersFragmentViewModel(get())
    }
}