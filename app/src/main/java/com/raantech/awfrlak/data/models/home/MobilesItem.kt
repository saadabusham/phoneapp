package com.raantech.awfrlak.data.models.home

import com.google.gson.annotations.SerializedName
import com.raantech.awfrlak.data.models.Price
import com.raantech.awfrlak.data.models.media.Media

data class MobilesItem(

	@field:SerializedName("additional_images")
	val additionalImages: List<Media>? = null,

	@field:SerializedName("is_active")
	val isActive: Boolean? = null,

	@field:SerializedName("color")
	val color: Color? = null,

	@field:SerializedName("related_mobiles")
	val relatedMobiles: List<MobilesItem>? = null,

	@field:SerializedName("is_new")
	val isNew: Boolean? = null,

	@field:SerializedName("sim_cards_numbers")
	val simCardsNumbers: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("store")
	val store: Store? = null,

	@field:SerializedName("storage")
	val storage: Storage? = null,

	@field:SerializedName("type")
	val type: Type? = null,

	@field:SerializedName("is_wishlist")
	val isWishlist: Boolean? = null,

	@field:SerializedName("is_in_stock")
	val isInStock: Boolean? = null,

	@field:SerializedName("base_image")
	val baseImage: Media? = null,

	@field:SerializedName("price")
	val price: Price? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)