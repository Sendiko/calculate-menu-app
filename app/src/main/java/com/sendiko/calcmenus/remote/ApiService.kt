package com.sendiko.calcmenus.remote

import com.sendiko.calcmenus.remote.requests.EmployeeLoginRequest
import com.sendiko.calcmenus.remote.requests.RestoLoginRequest
import com.sendiko.calcmenus.remote.requests.RestoRegisterRequest
import com.sendiko.calcmenus.remote.responses.EmployeeLoginResponse
import com.sendiko.calcmenus.remote.responses.RestoLoginResponse
import com.sendiko.calcmenus.remote.responses.RestoRegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("emp/login")
    fun employeeLogin(
        @Body employeeLoginRequest: EmployeeLoginRequest
    ): Call<EmployeeLoginResponse>

    @POST("resto/register")
    fun restoRegister(
        @Body restoRegisterRequest: RestoRegisterRequest
    ): Call <RestoRegisterResponse>

    @POST("resto/login")
    fun restoLogin(
        @Body  restoLoginRequest: RestoLoginRequest
    ): Call<RestoLoginResponse>

}