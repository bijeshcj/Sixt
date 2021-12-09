package com.bijesh.application.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bijesh.application.models.CarsItem
import com.bijesh.application.repos.CarRepository
import retrofit2.Call
import retrofit2.Response

class HomeViewModel constructor(private val repository: CarRepository) : ViewModel() {

    val carList = MutableLiveData<List<CarsItem>>()
    val errorMessage = MutableLiveData<String>()

    private val _listCars = MutableLiveData<List<CarsItem>>()
    val listCars : LiveData<List<CarsItem>> = _listCars

    fun setCarList(carList:List<CarsItem>){
        _listCars.value = carList
    }

    fun getCarList(){
        val response = repository.getAllCars()
        response.enqueue(object : retrofit2.Callback<List<CarsItem>> {
            override fun onResponse(call: Call<List<CarsItem>>, response: Response<List<CarsItem>>) {
                carList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CarsItem>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

}