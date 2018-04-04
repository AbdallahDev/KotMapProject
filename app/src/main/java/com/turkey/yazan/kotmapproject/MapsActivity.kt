package com.turkey.yazan.kotmapproject

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var m: Marker? = null

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        var manager = getSystemService(Context.LOCATION_SERVICE)
                as LocationManager
        var listener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {
                if (m != null)
                    m?.remove()
                val sydney = LatLng(p0.longitude, p0.latitude)
                m = mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14F))
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

            }

            override fun onProviderEnabled(p0: String?) {

            }

            override fun onProviderDisabled(p0: String?) {

            }

        }

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 0F, listener)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(32.001235, 35.878295)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 20F))
        mMap.setOnMapClickListener { latLng ->
            Toast.makeText(applicationContext, "" + latLng.longitude + "," + latLng.latitude, Toast.LENGTH_LONG).show()
        }
    }
}
