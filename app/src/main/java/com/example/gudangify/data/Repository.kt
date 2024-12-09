package com.example.gudangify.data

import android.content.Context
import com.example.gudangify.model.BarangEntity

class Repository constructor(context: Context) {
    val pref = SharedPreferencesHandler.getInstance(context)
    val room = RoomDatabaseHandler.getInstance(context)

    fun saveOnboardProgress() {
        pref.saveOnboardProgress()
    }

    fun resetOnboardProgress() {
        pref.resetOnboardProgress()
    }

    fun getOnboardProgress(): Boolean = pref.getOnboardProgress()

    suspend fun insert(item: BarangEntity) = room.insert(item)

    suspend fun deleteById(id: Int) = room.deleteById(id)

    suspend fun deleteAll() = room.deleteAll()

    suspend fun getAll(): List<BarangEntity> = room.getAll()

    suspend fun updateQuantity(id: Int, quantity: Int) = room.updateQuantity(id, quantity)
}