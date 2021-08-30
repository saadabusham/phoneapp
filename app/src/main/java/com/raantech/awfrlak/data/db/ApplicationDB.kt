package com.raantech.awfrlak.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raantech.awfrlak.data.daos.local.cart.CartLocalDao
import com.raantech.awfrlak.data.db.typeconverter.CategoryConverter
import com.raantech.awfrlak.data.db.typeconverter.MediaConverter
import com.raantech.awfrlak.data.db.typeconverter.MediaListConverter
import com.raantech.awfrlak.data.db.typeconverter.PriceConverter
import com.raantech.awfrlak.data.models.accessories.Accessory

@Database(
        entities = [
            Accessory::class
        ], version = 1
)

@TypeConverters(
    CategoryConverter::class,
    MediaConverter::class,
    MediaListConverter::class,
    PriceConverter::class
)

abstract class ApplicationDB : RoomDatabase() {

    abstract fun cartLocalDao(): CartLocalDao

    companion object {
        const val DATABASE_NAME = "awfrlak.db"
        const val TABLE_CART = "cartTable"
    }

}