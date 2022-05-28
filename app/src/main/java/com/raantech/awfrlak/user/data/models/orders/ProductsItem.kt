package com.raantech.awfrlak.user.data.models.orders

import com.google.gson.annotations.SerializedName

data class ProductsItem(

	@field:SerializedName("qty")
	val qty: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null
)