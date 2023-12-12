package com.sendiko.calcmenus.remote

import com.sendiko.calcmenus.remote.requests.EmployeeLoginRequest
import com.sendiko.calcmenus.remote.requests.RestoLoginRequest
import com.sendiko.calcmenus.remote.requests.RestoRegisterRequest
import com.sendiko.calcmenus.remote.responses.EmployeeLoginResponse
import com.sendiko.calcmenus.remote.responses.EmployeeLogoutResponse
import com.sendiko.calcmenus.remote.responses.GetMenuResponse
import com.sendiko.calcmenus.remote.responses.RestoLoginResponse
import com.sendiko.calcmenus.remote.responses.RestoLogoutResponse
import com.sendiko.calcmenus.remote.responses.RestoRegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

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

    @POST("resto/logout")
    fun restoLogout(
        @Header("Authorization") token: String
    ): Call<RestoLogoutResponse>

    @POST("emp/logout")
    fun employeeLogout(
        @Header("Authorization") token: String
    ): Call<EmployeeLogoutResponse>

    @GET("menu/{id}")
    fun getMenu(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Call<GetMenuResponse>

}