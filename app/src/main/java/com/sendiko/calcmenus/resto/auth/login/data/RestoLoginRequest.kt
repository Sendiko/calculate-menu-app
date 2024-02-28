package com.sendiko.calcmenus.resto.auth.login.data

import com.google.gson.annotations.SerializedName

data class RestoLoginRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
