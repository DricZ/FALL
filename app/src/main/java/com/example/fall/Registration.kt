package com.example.fall

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class Registration : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val btnNextRegist = findViewById<Button>(R.id.buttonNextRegist)
//        val etUsr = findViewById<EditText>(R.id.editTextUsr)
        val etUsr = findViewById<EditText>(R.id.editTextUsr)
        val etPass = findViewById<EditText>(R.id.editTextTextPassword)
        val etAge = findViewById<EditText>(R.id.editTextNumberSigned2)


//        CoroutineScope(Dispatchers.Main).async {
            btnNextRegist.setOnClickListener{

                if (etUsr.text.toString() != "" || etPass.text.toString() != "") {
                    val regist2Intent = Intent(this@Registration, Registration2::class.java)
                    startActivity(regist2Intent)
                } else {
                    Log.d("FAILED", "gagal next")
                    AlertDialog.Builder(this@Registration)
                        .setTitle("DATA NOT FILLED")
                        .setMessage("Back to fill data!")
                        .setNeutralButton("Back",
                        DialogInterface.OnClickListener{dialog, which ->
                            Toast.makeText(this@Registration, "Mohon lengkapi data di atas", Toast.LENGTH_LONG).show()
                        })
                }
//            }
        }
    }
}