package com.technzone.phoneapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.technzone.phoneapp.data.daos.local.cart.CartLocalDao
import com.technzone.phoneapp.data.db.typeconverter.CategoryConverter
import com.technzone.phoneapp.data.db.typeconverter.MediaConverter
import com.technzone.phoneapp.data.db.typeconverter.MediaListConverter
import com.technzone.phoneapp.data.db.typeconverter.PriceConverter
import com.technzone.phoneapp.data.models.accessories.Accessory

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
        const val DATABASE_NAME = "phoneapp.db"
        const val TABLE_CART = "cartTable"
    }

}