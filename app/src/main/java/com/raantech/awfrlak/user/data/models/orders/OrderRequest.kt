package com.raantech.awfrlak.user.data.models.orders

import com.google.gson.annotations.SerializedName

data class OrderRequest(

	@field:SerializedName("payment_method")
	var paymentMethod: String? = null,

	@field:SerializedName("products")
	var products: List<ProductsItem>? = null
)