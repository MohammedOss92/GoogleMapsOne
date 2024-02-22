package com.sarrawi.maps.misc

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.google.android.gms.maps.model.LatLng
import com.sarrawi.maps.R

class Overlays {

    val losAngeles = LatLng(34.04692127928215, -118.24748421830992)

    fun addGroundOverlay(map:GoogleMap){
        val groundOverlay = map.addGroundOverlay(
            GroundOverlayOptions().apply {
                position(losAngeles,1000f,1000f)
                image(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker))
            }
        )
    }
}