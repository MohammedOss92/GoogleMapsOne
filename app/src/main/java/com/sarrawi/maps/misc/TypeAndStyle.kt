package com.sarrawi.maps.misc

import android.content.Context
import android.util.Log
import android.view.MenuItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions
import com.sarrawi.maps.R

class TypeAndStyle {

    fun setMapStyle(googleMap: GoogleMap,con:Context) {
        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    con, R.raw.style
                )
            )
            if (!success) {
                Log.d("Maps", "Faild to add style.")
            }
        } catch (e: Exception) {
            Log.d("Maps", e.toString())
        }
    }

    fun setMapType(item:MenuItem,map:GoogleMap){
        when(item.itemId){
            R.id.normal_map ->{
                map.mapType = GoogleMap.MAP_TYPE_NORMAL
            }

            R.id.hybrid_map ->{
                map.mapType = GoogleMap.MAP_TYPE_HYBRID
            }

            R.id.satellite_map ->{
                map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }

            R.id.none_map ->{
                map.mapType = GoogleMap.MAP_TYPE_NONE
            }

            R.id.terrain_map ->{
                map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }

        }
    }
}