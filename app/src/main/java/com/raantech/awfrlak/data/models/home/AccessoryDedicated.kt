package com.raantech.awfrlak.data.models.home

import com.google.gson.annotations.SerializedName

data class AccessoryDedicated(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)