package com.sendiko.calcmenus.repository

import com.sendiko.calcmenus.remote.ApiConfig
import com.sendiko.calcmenus.remote.requests.EmployeeLoginRequest

class EmployeeRepository {

    private val client = ApiConfig.getInstance()

    fun employeeLogin(employeeLoginRequest: EmployeeLoginRequest) = client.employeeLogin(employeeLoginRequest)
}