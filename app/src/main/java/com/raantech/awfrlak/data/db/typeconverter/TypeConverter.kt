package com.raantech.awfrlak.data.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raantech.awfrlak.data.models.Price
import com.raantech.awfrlak.data.models.home.Store
import java.lang.reflect.Type

class TypeConverter {

    @TypeConverter
    fun storedStringToObject(value: String?): com.raantech.awfrlak.data.models.home.Type? {
        val type: Type = object : TypeToken<com.raantech.awfrlak.data.models.home.Type?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun objectToStoredString(data: com.raantech.awfrlak.data.models.home.Type?): String? {
        val gson = Gson()
        return gson.toJson(data)
    }
}