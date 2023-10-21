package com.sendiko.calcmenus.remote.responses

import com.google.gson.annotations.SerializedName

data class RestoRegisterResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
