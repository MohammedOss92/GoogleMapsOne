package com.sarrawi.maps

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileObserver.ACCESS
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.blue
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.sarrawi.maps.databinding.ActivityMapsBinding
import com.sarrawi.maps.misc.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.Manifest
import android.annotation.SuppressLint

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewport by lazy { CameraAndViewport() }

    val losAngeles = LatLng(34.04692127928215, -118.24748421830992)
    val amman = LatLng(31.99138298247842, 35.88583270469937)
    private val shapes by lazy{Shapes()}
    private val overlays by lazy{Overlays()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typeAndStyle.setMapType(item,map)
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera

        val losAngelesMarker = map.addMarker(MarkerOptions()
            .position(amman)
            .title("Marker in Sydney")
            .snippet("fdsfsd"))



        losAngelesMarker?.tag="Resturant"
//        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(amman,10f))
//        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.sydney))
        map.uiSettings.apply {
            //btn zoom + -
            isZoomControlsEnabled = true
            isMyLocationButtonEnabled = true

        }


        map.setInfoWindowAdapter(CustomInfoAdapter(this))


        typeAndStyle.setMapStyle(map,this)
        checkLocationPermission()
//        overlays.addGroundOverlay(map)
//        shapes.addPollygon(map)

//        lifecycleScope.launch {
//            shapes.addPollyLine(map)
//        }



//        lifecycleScope.launch {
//            delay(4000L)
//            losAngelesMarker?.remove()
//        }
////            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.sydney),2000,object :GoogleMap.CancelableCallback{
////                override fun onCancel() {
////                    Toast.makeText(this@MapsActivity,"onCancel",Toast.LENGTH_SHORT).show()
////                }
////
////                override fun onFinish() {
////                    Toast.makeText(this@MapsActivity,"Finished",Toast.LENGTH_SHORT).show()
////
////                }
////
////            })
////            map.animateCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds,100),2000,null)
////            map.setLatLngBoundsForCameraTarget(cameraAndViewport.melbourneBounds)
//        }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
            Toast.makeText(this, "Already Enabled", Toast.LENGTH_SHORT).show()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(ACCESS_FINE_LOCATION),
            1
        )
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode!=1){
            return
        }
        if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show()
            map.isMyLocationEnabled = true
        }else{
            Toast.makeText(this, "we need per", Toast.LENGTH_SHORT).show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



}