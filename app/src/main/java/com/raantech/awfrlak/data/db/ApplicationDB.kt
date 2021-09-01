package com.raantech.awfrlak.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raantech.awfrlak.data.daos.local.cart.CartLocalDao
import com.raantech.awfrlak.data.daos.local.cart.MobileCartLocalDao
import com.raantech.awfrlak.data.db.typeconverter.*
import com.raantech.awfrlak.data.models.accessories.Accessory
import com.raantech.awfrlak.data.models.home.MobilesItem

@Database(
        entities = [
            Accessory::class,
            MobilesItem::class
        ], version = 1
)

@TypeConverters(
        CategoryConverter::class,
        MediaConverter::class,
        MediaListConverter::class,
        PriceConverter::class,
        ColorConverter::class,
        MobileListConverter::class,
        StoreConverter::class,
        StorageConverter::class,
        TypeConverter::class
)

abstract class ApplicationDB : RoomDatabase() {

    abstract fun cartLocalDao(): CartLocalDao
    abstract fun mobileCartLocalDao(): MobileCartLocalDao

    companion object {
        const val DATABASE_NAME = "awfrlak.db"
        const val TABLE_CART = "cartTable"
        const val TABLE_MOBILE_CART = "mobileCartTable"
    }

}