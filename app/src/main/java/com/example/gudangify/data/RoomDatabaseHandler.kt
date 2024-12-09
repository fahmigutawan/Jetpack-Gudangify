package com.example.gudangify.data

import android.content.Context
import androidx.room.Room
import com.example.gudangify.data.room.BarangDao
import com.example.gudangify.data.room.MyRoomDb
import com.example.gudangify.model.BarangEntity

class RoomDatabaseHandler {
    lateinit var barangDao: BarangDao

    private constructor()
    private constructor(context: Context) {
        barangDao = Room.databaseBuilder(
            context,
            MyRoomDb::class.java,
            "room"
        ).build().barangDao()
    }

    companion object {
        private lateinit var roomDatabaseHandler: RoomDatabaseHandler
        fun getInstance(context: Context): RoomDatabaseHandler {
            if (!this::roomDatabaseHandler.isInitialized) {
                roomDatabaseHandler = RoomDatabaseHandler(context)
            }
            return roomDatabaseHandler
        }
    }

    suspend fun insert(item: BarangEntity) {
        barangDao.insert(item)
    }

    suspend fun deleteById(id: Int) {
        barangDao.deleteById(id)
    }

    suspend fun deleteAll() {
        barangDao.deleteAll()
    }

    fun getAll() = barangDao.getAll()

    suspend fun updateQuantity(id: Int, quantity: Int) {
        barangDao.updateQuantity(id, quantity)
    }
}