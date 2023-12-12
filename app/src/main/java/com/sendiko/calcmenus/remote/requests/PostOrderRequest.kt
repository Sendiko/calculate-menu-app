package com.sendiko.calcmenus.remote.requests

import com.google.gson.annotations.SerializedName

data class PostOrderRequest(

	@field:SerializedName("menu_ids")
	val menuIds: List<String?>? = null,

	@field:SerializedName("total_price")
	val totalPrice: Int? = null,

	@field:SerializedName("table_number")
	val tableNumber: String? = null,

	@field:SerializedName("menu_notes")
	val menuNotes: List<String?>? = null
)
