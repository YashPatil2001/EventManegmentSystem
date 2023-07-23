package com.example.eventManagementSystem.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.eventManagementSystem.CustomerAPI.CustomerModule
import com.google.gson.Gson

/**
 * Created by Yash on 7/23/2023.
 */
object SharedPref {

    private const val PREFS_NAME = "MyPrefs"
    private const val LOGGED_IN_USER = "logged_in_user"
    private const val ADMIN_LOGGED_IN = "admin_logged_in"
    private lateinit var sharedPrefs: SharedPreferences

    fun init(context: Context) {
        sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }


    /**
     * LOG IN USER
     */
    //region
    fun logInUser(user: CustomerModule) {
        val userStr = Gson().toJson(user)
        putString(LOGGED_IN_USER, userStr)
    }
    //endregion


    /**
     * GET LOGGED IN USER
     */
    //region
    fun getLoggedInUser(): CustomerModule? {
        val userStr = getString(LOGGED_IN_USER)
        userStr ?: return null
        return Gson().fromJson(userStr, CustomerModule::class.java)
    }
    //endregion


    /**
     * LOG IN ADMIN
     */
    //region
    fun saveAdmin() {
        putBoolean(ADMIN_LOGGED_IN, true)
    }
    //endregion

    /**
     * CHECK ADMIN LOGIN
     */
    //region
    fun checkAdminLogin(): Boolean {
        return getBoolean(ADMIN_LOGGED_IN)
    }
    //endregion


    /**
     * CLEAR ALL DATA
     */
    //region
    fun clearData() {
        sharedPrefs.edit().clear().apply()
    }
    //endregion

    private fun putString(key: String, value: String) {
        sharedPrefs.edit().putString(key, value).apply()
    }

    private fun getString(key: String): String? {
        return sharedPrefs.getString(key, null)
    }
    private fun putBoolean(key: String, value: Boolean) {
        sharedPrefs.edit().putBoolean(key, value).apply()
    }
    private fun getBoolean(key: String): Boolean {
        return sharedPrefs.getBoolean(key, false)
    }
}