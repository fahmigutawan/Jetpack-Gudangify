package com.example.gudangify.presentation.list

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.gudangify.data.Repository

class ListViewModel : ViewModel(){
    private lateinit var repo: Repository

    fun init(context: Context){
        if(!this::repo.isInitialized) repo = Repository(context)
    }
}