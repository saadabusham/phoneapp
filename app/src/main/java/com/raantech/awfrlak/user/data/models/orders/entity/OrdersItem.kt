package com.raantech.awfrlak.user.data.models.orders.entity

import com.google.gson.annotations.SerializedName
import com.raantech.awfrlak.user.data.models.Price
import com.raantech.awfrlak.user.data.models.home.Store
import java.io.Serializable

data class OrdersItem(

    @field:SerializedName("total")
    val total: Price? = null,

    @field:SerializedName("sub_total")
    val subTotal: Price? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("order_group_number")
    val orderGroupNumber: String? = null,

    @field:SerializedName("vat_percentage")
    val vatPercentage: Int? = null,

    @field:SerializedName("store")
    val store: Store? = null,

    @field:SerializedName("vat_price")
    val vatPrice: Price? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("prodcuts")
    val prodcuts: List<OrderProduct>? = null
):Serializable