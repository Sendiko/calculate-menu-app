package com.sendiko.calcmenus.remote.responses

import com.google.gson.annotations.SerializedName

data class EmployeeLogoutResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("token")
	val token: String? = null
)
