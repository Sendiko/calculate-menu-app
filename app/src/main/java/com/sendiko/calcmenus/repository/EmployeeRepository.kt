package com.sendiko.calcmenus.repository

import com.sendiko.calcmenus.remote.ApiConfig
import com.sendiko.calcmenus.remote.requests.EmployeeLoginRequest

class EmployeeRepository {

    private val client = ApiConfig.getInstance()

    fun postEmployeeLogin(employeeLoginRequest: EmployeeLoginRequest) = client.employeeLogin(employeeLoginRequest)
}