package com.sendiko.calcmenus.employee.login.data

import com.google.gson.annotations.SerializedName

data class EmployeeLoginResponse(

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("user")
	val user: User? = null,

    @field:SerializedName("status")
	val status: Int? = null,

    @field:SerializedName("token")
	val token: String? = null
)

data class User(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("restaurant_id")
	val restaurantId: String? = null,

	@field:SerializedName("employee_id")
	val employeeId: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)
