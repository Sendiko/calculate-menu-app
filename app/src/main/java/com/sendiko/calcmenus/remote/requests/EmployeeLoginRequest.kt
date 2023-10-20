package com.sendiko.calcmenus.remote.requests

import com.google.gson.annotations.SerializedName

data class EmployeeLoginRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
