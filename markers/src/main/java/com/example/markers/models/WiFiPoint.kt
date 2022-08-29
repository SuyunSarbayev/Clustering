package com.example.markers.models

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class WiFiPoint(var id: String,
                var latitude: Double,
                var longitude: Double): ClusterItem {

    override fun getPosition(): LatLng {
        return LatLng(latitude, longitude)
    }

    override fun getTitle(): String {
        return id
    }

    override fun getSnippet(): String? {
        return null
    }
}