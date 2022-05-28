package com.raantech.awfrlak.user.data.models.orders

import com.google.gson.annotations.SerializedName

data class CreateOrderResponse(

	@field:SerializedName("redirect")
	val redirect: String? = null
)