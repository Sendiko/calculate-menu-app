package com.sendiko.calcmenus.remote

import com.sendiko.calcmenus.remote.requests.EmployeeLoginRequest
import com.sendiko.calcmenus.remote.responses.EmployeeLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("emp/login")
    fun employeeLogin(
        @Body employeeLoginRequest: EmployeeLoginRequest
    ): Call<EmployeeLoginResponse>

}