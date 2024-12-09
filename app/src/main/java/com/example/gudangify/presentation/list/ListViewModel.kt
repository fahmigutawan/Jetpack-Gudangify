package com.example.gudangify.presentation.list

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gudangify.data.Repository
import com.example.gudangify.model.BarangEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ListViewModel : ViewModel(){
    private lateinit var repo: Repository
    val barang = MutableStateFlow<List<BarangEntity>>(emptyList())

    val searchInput = mutableStateOf("")
    val expandedItemId = mutableStateOf<Int?>(null)
    val showAddDialog = mutableStateOf(false)
    val addInput = mutableStateOf("")

    fun init(context: Context){
        if(!this::repo.isInitialized) repo = Repository(context)
    }

    fun getAllBarang(){
        viewModelScope.launch {
            repo.getAll().collect{
                barang.value = it
            }
        }
    }

    fun updateQuantityBarang(id:Int, quantity: Int){
        viewModelScope.launch {
            repo.updateQuantity(id, quantity)
        }
    }

    fun addBarang(item: BarangEntity){
        viewModelScope.launch {
            repo.insert(item)
        }
    }

    fun deleteById(id:Int){
        viewModelScope.launch {
            repo.deleteById(id)
        }
    }
}