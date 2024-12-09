package com.example.gudangify.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHandler {
    private lateinit var pref: SharedPreferences
    private constructor()
    private constructor(context: Context) {
        pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    }

    companion object {
        private lateinit var prefHandler: SharedPreferencesHandler
        fun getInstance(context: Context): SharedPreferencesHandler {
            if(!this::prefHandler.isInitialized){
                prefHandler = SharedPreferencesHandler(context)
            }

            return prefHandler
        }
    }

    fun saveOnboardProgress(){
        pref.edit().putBoolean("onboard", true).apply()
    }

    fun resetOnboardProgress(){
        pref.edit().putBoolean("onboard", false).apply()
    }

    fun getOnboardProgress(): Boolean = pref.getBoolean("onboard", false)
}