package com.raantech.awfrlak.user.data.models.home

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Type(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
): Serializable