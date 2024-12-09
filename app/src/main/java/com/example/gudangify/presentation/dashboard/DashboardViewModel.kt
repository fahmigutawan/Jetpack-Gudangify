package com.example.gudangify.presentation.dashboard

import android.content.Context
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gudangify.data.Repository
import com.example.gudangify.model.BarangEntity
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    private lateinit var repo: Repository
    val barang = mutableStateListOf<BarangEntity>()
    val itemCount = derivedStateOf { barang.size }
    val quantityCount = derivedStateOf { barang.sumOf { it.quantity } }

    fun init(context: Context) {
        if (!this::repo.isInitialized) repo = Repository(context)
    }

    fun getAllBarang() {
        viewModelScope.launch {
            barang.clear()
            barang.addAll(repo.getAll())
        }
    }
}