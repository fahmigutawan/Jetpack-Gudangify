package com.example.gudangify.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gudangify.model.BarangEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BarangDao {
    @Insert
    suspend fun insert(item: BarangEntity)

    @Query("delete from barangentity where id=:id")
    suspend fun deleteById(id:Int)

    @Query("delete from barangentity")
    suspend fun deleteAll()

    @Query("select * from barangentity")
    fun getAll(): Flow<List<BarangEntity>>

    @Query("update barangentity set quantity=:quantity where id=:id")
    suspend fun updateQuantity(id:Int, quantity: Int)
}