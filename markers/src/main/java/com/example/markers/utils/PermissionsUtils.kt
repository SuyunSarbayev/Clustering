package com.example.markers.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionsUtils(var context: Context){

    fun hasPermissions(permissions: Array<String>): Boolean{
        for(permission in permissions){
            if(ContextCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
                return true
            }
        }
        return false
    }

    fun initiateRequestPermission(fragment: Fragment,
                                  permissions: Array<String>,
                                  requestCode: Int){
        fragment.requestPermissions(permissions, requestCode)
    }
}