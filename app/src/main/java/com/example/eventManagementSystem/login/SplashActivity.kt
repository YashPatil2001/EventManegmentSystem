package com.example.eventManagementSystem.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.eventManagementSystem.Activity.AdminPanel
import com.example.eventManagementSystem.Activity.CustomerPanel
import com.example.eventManagementSystem.R
import com.example.eventManagementSystem.utils.SharedPref

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()



    }

    private fun init() {
        if(SharedPref.checkAdminLogin()) {
            startActivity(Intent(this, AdminPanel::class.java))
            finish()
            return
        }

        if(SharedPref.getLoggedInUser() != null) {
            val user = SharedPref.getLoggedInUser()!!
            val intent = Intent(this, CustomerPanel::class.java)
            intent.putExtra("Tp", user.student_Tp)
            intent.putExtra("Degree", user.student_DegreeField)
            intent.putExtra("Name", user.student_Name)
            intent.putExtra("Email", user.student_Email)
            intent.putExtra("PhoneNo", user.student_PhoneNumber)
            startActivity(intent)
            finish()
            return
        }
        startActivity(Intent(this, LoginPage::class.java))
        finish()
    }
}