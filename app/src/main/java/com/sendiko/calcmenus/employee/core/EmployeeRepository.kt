package com.sendiko.calcmenus.employee.core

import com.sendiko.calcmenus.core.network.ApiService
import com.sendiko.calcmenus.employee.login.data.EmployeeLoginRequest
import com.sendiko.calcmenus.employee.order_resume.data.PostOrderRequest
import javax.inject.Inject

class EmployeeRepository @Inject constructor(private val client: ApiService) {

    fun employeeLogin(employeeLoginRequest: EmployeeLoginRequest) = client.employeeLogin(employeeLoginRequest)

    fun employeeLogout(token: String) = client.employeeLogout(token)

    fun getMenu(token: String, restoId: String) = client.getMenu(restoId, token)

    fun postOrder(token: String, request: PostOrderRequest) = client.postOrder(token, request)
}