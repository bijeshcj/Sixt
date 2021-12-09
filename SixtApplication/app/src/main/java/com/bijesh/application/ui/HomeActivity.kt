package com.bijesh.application.ui

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.bijesh.application.databinding.ActivityHomeBinding
import com.bijesh.application.network.SixtWebService
import com.bijesh.application.repos.CarRepository
import com.bijesh.application.ui.ui.main.SectionsPagerAdapter
import com.bijesh.application.viewmodels.AppViewModelFactory
import com.bijesh.application.viewmodels.HomeViewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val sixtWebService : SixtWebService = SixtWebService.getInstance()
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)




//        homeViewModel = ViewModelProvider(this,AppViewModelFactory(CarRepository(sixtWebService))).get(HomeViewModel::class.java)
//
//        homeViewModel.getCarList()


        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.fab

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

//        homeViewModel.carList.observe(this,{
//            Log.d("HomeActivity",it.get(0).modelName)
//        })
    }
}