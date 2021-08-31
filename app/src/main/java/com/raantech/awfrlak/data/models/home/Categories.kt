package com.raantech.awfrlak.data.models.home

import com.raantech.awfrlak.data.enums.CategoriesEnum

data class Category(
    val categoryEnum: CategoriesEnum,
    val title: String
)
