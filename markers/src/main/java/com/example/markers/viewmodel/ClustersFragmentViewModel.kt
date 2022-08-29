package com.example.markers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.markers.models.WiFiPoint
import com.example.markers.utils.Reader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClustersFragmentViewModel(
    var reader: Reader
): ViewModel() {

    lateinit var ioScope: CoroutineScope
    lateinit var uiScope: CoroutineScope

    init {
        initializeScopes()
    }

    fun initializeScopes(){
        ioScope = CoroutineScope(Dispatchers.IO)
        uiScope = CoroutineScope(Dispatchers.Main)
    }

    var loadedPointsLiveData = MutableLiveData<List<WiFiPoint>>()

    var loaderLiveData = MutableLiveData<Boolean>()

    fun initiateReadFile(){
        ioScope.launch {
            loaderLiveData.postValue(true)
            var items = reader.initiateReadFile()
            loadedPointsLiveData.postValue(items)
            loaderLiveData.postValue(false)
        }
    }
}