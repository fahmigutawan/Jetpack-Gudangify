package com.example.gudangify.presentation.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gudangify.data.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel(){
    private lateinit var repo: Repository

    fun init(context: Context){
        if(!this::repo.isInitialized) repo = Repository(context)
    }

    fun precheck(
        check:(isOnboardFinish:Boolean) -> Unit
    ){
        viewModelScope.launch {
            delay(2000)
            check(repo.getOnboardProgress())
        }
    }
}