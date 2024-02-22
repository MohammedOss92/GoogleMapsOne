package com.sarrawi.maps.misc

import android.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.sarrawi.maps.R
import kotlinx.coroutines.delay

class Shapes {

    val losAngeles = LatLng(34.04692127928215, -118.24748421830992)
    val newYork = LatLng(40.71614203933524, -74.00440676650565)
    val madrid = LatLng(40.639871895206674, -3.5627974558481665)
    val panama = LatLng(8.983565, -79.519729)

    private val p0=LatLng(34.61111283456,-119.61055187107762)
    private val p1=LatLng(34.599014993181534,-117.15922754262057)
    private val p2=LatLng(33.550677353674885,-117.14616252288198)
    private val p3=LatLng(33.54387186558186,-119.81469280436997)

    private val p00 = LatLng(34.3634293763002 , -118.82828381410476)
    private val p01 = LatLng(34.334628180516 , -117.87780362812079)
    private val p02 = LatLng(33.86857373492017, -117.8435079513069)
    private val p03 = LatLng(33.80888822068028, -118.8266506866374)

    suspend fun addPollyLine(map:GoogleMap){
//        val pattern = listOf(Dot(),Gap(30f),Dash(50f))

        val pollyline = map.addPolyline(
            PolylineOptions().apply {
                add(losAngeles,newYork,madrid)
                width(40f)
                color(Color.BLUE)
                geodesic(true)
                clickable(true)
//                pattern(pattern)
            }
        )

        delay(5000)
        val newList = listOf(
            losAngeles,panama,madrid
        )
        pollyline.points = newList
    }

    fun addPollygon(map:GoogleMap){
        val polygon = map.addPolygon(
            PolygonOptions().apply {
                add(p0,p1,p2,p3)
                fillColor(R.color.black)
                strokeColor(R.color.black)
                addHole(listOf(p00,p01,p02,p03))
            }
        )
    }

     fun addCircle(map:GoogleMap){
        val circle = map.addCircle(
            CircleOptions().apply {
                center(losAngeles)
                radius(50000.0)
                fillColor(R.color.purple_200)
            }
        )

    }
}