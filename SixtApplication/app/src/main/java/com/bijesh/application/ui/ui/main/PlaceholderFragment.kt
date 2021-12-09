package com.bijesh.application.ui.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bijesh.application.databinding.FragmentHomeBinding
import com.bijesh.application.models.CarsItem
import com.bijesh.application.network.SixtWebService
import com.bijesh.application.repos.CarRepository
import com.bijesh.application.ui.adapters.CarAdapter
import com.bijesh.application.viewmodels.AppViewModelFactory
import com.bijesh.application.viewmodels.HomeViewModel


/**
 * A placeholder fragment containing a recycler view to display the car details
 */
class PlaceholderFragment : BaseFragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentHomeBinding? = null



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root

//        sharedViewModel = ViewModelProvider(this, AppViewModelFactory(CarRepository(sixtWebService))).get(HomeViewModel::class.java)

//        val textView: TextView = binding.sectionLabel
//        pageViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        var recyclerView : RecyclerView = binding.recyclerViewCars
        var  carAdapter = CarAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = carAdapter




        sharedViewModel.getCarList()
//
        sharedViewModel.carList.observe(viewLifecycleOwner, Observer {
//            Log.d("PlaceholderFragment",it.get(0).modelName)
            carAdapter.setCars(it)
//            listCarItems = it
            ex.exchange(it)
        })

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"
        private lateinit var ex: BaseFragment.CommunicateData

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(exchange: BaseFragment.CommunicateData): PlaceholderFragment {
            return PlaceholderFragment().apply {
                ex = exchange
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}