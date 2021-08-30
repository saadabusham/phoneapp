package com.raantech.awfrlak.data.models.home

import android.media.tv.TvContract
import com.google.gson.annotations.SerializedName
import com.raantech.awfrlak.data.models.media.Media

data class Store(

	@field:SerializedName("cover")
	val cover: Any? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("responsible_person")
	val responsiblePerson: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("logo")
	val logo: Media? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)