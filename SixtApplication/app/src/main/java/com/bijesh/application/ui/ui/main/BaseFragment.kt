package com.bijesh.application.ui.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bijesh.application.models.CarsItem
import com.bijesh.application.network.SixtWebService
import com.bijesh.application.repos.CarRepository
import com.bijesh.application.viewmodels.AppViewModelFactory
import com.bijesh.application.viewmodels.HomeViewModel

open class BaseFragment : Fragment() {

     val sixtWebService : SixtWebService = SixtWebService.getInstance()
     lateinit var  sharedViewModel : HomeViewModel
//     lateinit var listCarItems : List<CarsItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(this, AppViewModelFactory(CarRepository(sixtWebService))).get(HomeViewModel::class.java)
    }

    interface CommunicateData{
        fun exchange(lst: List<CarsItem>)
    }

}