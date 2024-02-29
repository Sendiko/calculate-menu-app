package com.sendiko.calcmenus.resto.core

import com.sendiko.calcmenus.core.network.ApiService
import com.sendiko.calcmenus.resto.auth.login.data.RestoLoginRequest
import com.sendiko.calcmenus.resto.auth.register.data.RestoRegisterRequest
import javax.inject.Inject

class RestoRepository @Inject constructor(private val client: ApiService) {

    fun restoRegister(restoRegisterRequest: RestoRegisterRequest) = client.restoRegister(restoRegisterRequest)

    fun restoLogin(restoLoginRequest: RestoLoginRequest) = client.restoLogin(restoLoginRequest)

    fun restoLogout(token: String) = client.restoLogout(token)
}