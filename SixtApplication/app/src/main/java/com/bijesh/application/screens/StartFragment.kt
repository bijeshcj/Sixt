package com.bijesh.application.screens

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bijesh.application.R
import com.bijesh.application.databinding.FragmentStartBinding
import com.bijesh.application.network.SixtWebService
import com.bijesh.application.repos.CarRepository
import com.bijesh.application.ui.adapters.CarAdapter
import com.bijesh.application.ui.ui.main.PlaceholderFragment
import com.bijesh.application.viewmodels.AppViewModelFactory
import com.bijesh.application.viewmodels.HomeViewModel
import java.util.ArrayList

class StartFragment : Fragment() {

    private var binding : FragmentStartBinding? = null
    val sixtWebService : SixtWebService = SixtWebService.getInstance()
    lateinit var  sharedViewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this

        sharedViewModel = ViewModelProvider(this, AppViewModelFactory(CarRepository(sixtWebService))).get(HomeViewModel::class.java)
        sharedViewModel.getCarList()

        var recyclerView : RecyclerView? = binding?.recyclerViewCars
        var  carAdapter = CarAdapter()
        recyclerView?.layoutManager = LinearLayoutManager(this.context)
        recyclerView?.adapter = carAdapter

        sharedViewModel.carList.observe(viewLifecycleOwner, Observer {
            Log.d("StartFragment",it.get(0).modelName)
            carAdapter.setCars(it)
            sharedViewModel.setCarList(it)
//            listCarItems = it
//            PlaceholderFragment.ex.exchange(it)
        })

        binding?.button?.setOnClickListener({
            val bundle = Bundle()
            bundle.putParcelableArrayList("carList",sharedViewModel.listCars.value as ArrayList<out Parcelable>)
            findNavController().navigate(R.id.action_startFragment_to_mapsFragment,bundle)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}