package com.bijesh.application.repos

import com.bijesh.application.network.SixtWebService

class CarRepository constructor(private val sixtWebService: SixtWebService) {

    fun getAllCars() = sixtWebService.getCars()

}