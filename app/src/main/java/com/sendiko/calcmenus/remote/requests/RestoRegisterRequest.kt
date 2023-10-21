package com.sendiko.calcmenus.remote.requests

import com.google.gson.annotations.SerializedName

data class RestoRegisterRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("password_confirmation")
	val passwordConfirmation: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("phone_contact")
	val phoneContact: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
