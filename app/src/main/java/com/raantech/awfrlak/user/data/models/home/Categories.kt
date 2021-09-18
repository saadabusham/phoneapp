package com.raantech.awfrlak.user.data.models.home

import com.raantech.awfrlak.user.data.enums.CategoriesEnum

data class Category(
    val categoryEnum: CategoriesEnum,
    val title: String
)
