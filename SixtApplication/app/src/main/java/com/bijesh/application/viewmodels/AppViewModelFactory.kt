package com.bijesh.application.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bijesh.application.repos.CarRepository
import java.lang.IllegalArgumentException

class AppViewModelFactory constructor(private val repository: CarRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            HomeViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}