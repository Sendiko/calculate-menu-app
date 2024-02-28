package com.sendiko.calcmenus.resto.auth.login.data

import com.google.gson.annotations.SerializedName

data class RestoLoginResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("token")
	val token: String? = null
)
