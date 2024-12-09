package com.example.gudangify.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BarangEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val nama:String,
    val quantity:Int
)
