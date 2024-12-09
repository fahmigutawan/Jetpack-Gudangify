package com.example.gudangify.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gudangify.model.BarangEntity

@Database(
    entities = [
        BarangEntity::class
    ],
    version = 1
)
abstract class MyRoomDb : RoomDatabase() {
    abstract fun barangDao(): BarangDao
}