package com.example.eventManagementSystem.utils

import android.app.Application

/**
 * Created by Yash on 7/23/2023.
 */
class EventApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}