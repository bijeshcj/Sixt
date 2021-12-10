package com.bijesh.application.ui.ui.main


import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bijesh.application.R
import com.bijesh.application.models.CarsItem
import com.bijesh.application.ui.adapters.CarAdapter
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.RuntimeException
import java.lang.reflect.Field


class MapsFragment : BaseFragment(), OnMapReadyCallback, BaseFragment.CommunicateData {

    private  var mMap : GoogleMap? = null
    private var supportMapFragment = SupportMapFragment()
    private lateinit var listCars : ArrayList<CarsItem>
    private  var mutableLiveData = MutableLiveData<List<CarsItem>>()
    private  var mapFragment : SupportMapFragment? = null
    private var carAdapter = CarAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_maps, container, false)
         mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?


        mapFragment!!.getMapAsync(this)
//        supportMapFragment = mapFragment

        if(arguments?.getParcelableArrayList<Parcelable>("carList") != null){
            listCars = arguments?.getParcelableArrayList<Parcelable>("carList") as ArrayList<CarsItem>
        }

//        var carList: ArrayList<CarsItem> = arguments?.getParcelableArrayList<Parcelable>("carList") as ArrayList<CarsItem>
//
//
//        listCars = carList

        mutableLiveData.observe(viewLifecycleOwner, Observer {
            for (car in it) {
                val carLocation = LatLng(car.latitude, car.longitude)
                mMap?.addMarker(MarkerOptions().position(carLocation).title(car.licensePlate))
                mMap?.moveCamera(CameraUpdateFactory.newLatLng(carLocation))
            }
        })

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if(::listCars.isInitialized) {
            for (car in listCars) {
                val carLocation = LatLng(car.latitude, car.longitude)
                zoomTo(carLocation,car)
            }
        }

    }

    fun zoomTo(carLocation: LatLng, car: CarsItem){
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(carLocation,12f))
        var cameraPosition = CameraPosition.builder().target(carLocation).zoom(12f).tilt(40f).build()
        mMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        mMap?.addMarker(MarkerOptions().position(carLocation).draggable(false)
            .title(car.make))
    }


    companion object {



        @JvmStatic
        fun newInstance(): MapsFragment {
            return MapsFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun exchange(lst: List<CarsItem>) {
//        MapsInitializer.initialize(requireContext())
        mutableLiveData.postValue(lst)
        if(carAdapter == null){
            Log.e("Maps","carAdapter o")
        }
//        if(mMap == null){
//             supportMapFragment!!.getMapAsync(this)
//        }
        if(mapFragment != null) {
            mapFragment!!.getMapAsync(this)
//            listCars = lst
        }else{
            if(activity != null && isAdded) {
                mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
                mapFragment!!.getMapAsync(this)
            }
        }
    }




}