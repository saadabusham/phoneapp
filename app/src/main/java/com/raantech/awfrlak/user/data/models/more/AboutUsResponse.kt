package com.raantech.awfrlak.user.data.models.more

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AboutUsResponse(
	@field:SerializedName("about_Us")
	val aboutUs: String? = null
): Serializable