package com.technzone.phoneapp.data.models

import com.technzone.phoneapp.data.models.accessories.Accessory

data class Purchase(
    val id:Int? = 123456,
    val status: String,
    val price:Price? = Price("50.0","50.0 ر.س","ر.س"),
    val date:String? = "2 jan 2021",
    val items: List<Accessory>
) {
    fun itemsString(): String {
        return items.map { "x${it.count} ${it.name}" }.joinToString(separator = "\n")
    }
}
