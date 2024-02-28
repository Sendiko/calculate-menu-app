package com.sendiko.calcmenus.resto.core

import com.sendiko.calcmenus.core.network.ApiConfig
import com.sendiko.calcmenus.resto.auth.login.data.RestoLoginRequest
import com.sendiko.calcmenus.resto.auth.register.data.RestoRegisterRequest

class RestoRepository {

    private val client = ApiConfig.getInstance()

    fun restoRegister(restoRegisterRequest: RestoRegisterRequest) = client.restoRegister(restoRegisterRequest)

    fun restoLogin(restoLoginRequest: RestoLoginRequest) = client.restoLogin(restoLoginRequest)

    fun restoLogout(token: String) = client.restoLogout(token)
}