package com.raantech.awfrlak.user.data.models.orders.request

import com.google.gson.annotations.SerializedName
import com.raantech.awfrlak.user.data.models.orders.request.ProductsItem

data class OrderRequest(

	@field:SerializedName("payment_method")
	var paymentMethod: String? = null,

	@field:SerializedName("products")
	var products: List<ProductsItem>? = null
)