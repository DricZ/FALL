package com.example.fall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val btnSavePass = findViewById<Button>(R.id.buttonSavePass)
        val etNewPass =  findViewById<EditText>(R.id.editTextNewPass)
        val etRePass = findViewById<EditText>(R.id.editTextRePass)
        btnSavePass.setOnClickListener {
//            UBAH DAN SAVE PASSWORD BARU KE DB
            if (etNewPass.text.toString() == etRePass.text.toString()){
                etNewPass.setText("")
                etRePass.setText("")
                startActivity(Intent(this@ChangePasswordActivity, tesHome::class.java))

            }
            else{
                etRePass.setText("")
                Toast.makeText(this, "Mohon Periksa Kembali Password!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}