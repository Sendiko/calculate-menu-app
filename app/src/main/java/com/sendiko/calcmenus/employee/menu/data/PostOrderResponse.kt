package com.sendiko.calcmenus.employee.menu.data

import com.google.gson.annotations.SerializedName

data class PostOrderResponse(

	@field:SerializedName("menu_data")
	val menuData: List<MenuDataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("order")
	val order: Order? = null
)

data class Order(

	@field:SerializedName("transaction_id")
	val transactionId: String? = null,

	@field:SerializedName("menu_ids")
	val menuIds: String? = null,

	@field:SerializedName("total_price")
	val totalPrice: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("table_number")
	val tableNumber: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("restaurant_name")
	val restaurantName: String? = null,

	@field:SerializedName("payed")
	val payed: Int? = null,

	@field:SerializedName("menu_notes")
	val menuNotes: String? = null
)

data class MenuDataItem(

	@field:SerializedName("menu_price")
	val menuPrice: Int? = null,

	@field:SerializedName("menu_name")
	val menuName: String? = null,

	@field:SerializedName("menu_notes")
	val menuNotes: Any? = null
)
