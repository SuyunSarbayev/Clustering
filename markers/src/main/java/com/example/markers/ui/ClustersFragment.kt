package com.example.markers.ui

import android.Manifest.permission
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.base.BaseFragment
import com.example.markers.R
import com.example.markers.databinding.ClustersFragmentBinding
import com.example.markers.models.WiFiPoint
import com.example.markers.utils.PermissionsUtils
import com.example.markers.viewmodel.ClustersFragmentViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClustersFragment:
    BaseFragment<ClustersFragmentBinding>(),
    GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener,
    OnMapReadyCallback,
    ActivityCompat.OnRequestPermissionsResultCallback{

    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    var clusterManager: ClusterManager<WiFiPoint>? = null

    val clustersFragmentViewModel by viewModel<ClustersFragmentViewModel>()

    val permissionUtils by inject<PermissionsUtils>()

    var loader = DialogLoader("Loading WiFi points")

    lateinit var map: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeMap()
        initializeObservers()
    }

    fun initializeMap(){
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment_clusters) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun initializeLayout(): Int {
        return R.layout.fragment_clusters
    }

    private fun initializeClusters(map: GoogleMap) {
        clusterManager = ClusterManager(context, map)
        map.setOnCameraIdleListener(clusterManager)
        map.setOnMarkerClickListener(clusterManager)
        clustersFragmentViewModel.initiateReadFile()
    }

    fun initiateDisplayLoader(){
        loader.show(childFragmentManager, DialogLoader.TAG)
    }

    fun initiateDismissLoader(){
        loader.dismiss()
    }

    fun initializeObservers(){
        clustersFragmentViewModel.loadedPointsLiveData.observe(viewLifecycleOwner, {
            clusterManager?.addItems(it)
        })
        clustersFragmentViewModel.loaderLiveData.observe(viewLifecycleOwner, {
            when(it){
                true -> initiateDisplayLoader()
                else -> initiateDismissLoader()
            }
        })
    }

    @SuppressLint("MissingPermission")
    private fun initiateEnableMyLocation() {
        if (permissionUtils
                .hasPermissions(arrayOf(permission.ACCESS_FINE_LOCATION, permission.ACCESS_COARSE_LOCATION))) {
            map.isMyLocationEnabled = true
            return
        }

        permissionUtils.initiateRequestPermission(
            this,
            arrayOf(
                permission.ACCESS_FINE_LOCATION, permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onMyLocationButtonClick(): Boolean { return false; }

    override fun onMyLocationClick(p0: Location) {}

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (permissionUtils
                .hasPermissions(arrayOf(permission.ACCESS_FINE_LOCATION, permission.ACCESS_COARSE_LOCATION))) {
            initiateEnableMyLocation()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        initializeClusters(googleMap)
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
        initiateEnableMyLocation()
    }
}