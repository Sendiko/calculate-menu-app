package com.sendiko.calcmenus.repository

import com.sendiko.calcmenus.remote.ApiConfig
import com.sendiko.calcmenus.remote.requests.RestoRegisterRequest

class RestoRepository {

    private val client = ApiConfig.getInstance()

    fun restoRegister(restoRegisterRequest: RestoRegisterRequest) = client.restoRegister(restoRegisterRequest)
}