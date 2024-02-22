package com.sarrawi.maps.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class CameraAndViewport {

    val sydney:CameraPosition = CameraPosition.Builder()
        .target(LatLng( 34.04692127928215, -118.24748421830992))
        .zoom(17f)
        .bearing(100f)
        .tilt(45f)
            //3d
//        .tilt(0f)
        .build()

    val melbourneBounds = LatLngBounds(
        LatLng(-38.45007744433469,144.2546884913213),
        LatLng(-37.52250857924213, 145.56755471879967)
        )
}