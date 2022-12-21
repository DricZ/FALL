package com.example.fall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val txtRegist = findViewById<TextView>(R.id.txtSignUp)
        txtRegist.setOnClickListener {
            val toRegistIntent = Intent(this@Login, Registration::class.java)
            startActivity(toRegistIntent)
        }
    }
}