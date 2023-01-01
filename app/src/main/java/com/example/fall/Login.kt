package com.example.fall

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class Login : AppCompatActivity() {
    lateinit var db: FirebaseFirestore

    private var _userN : MutableList<String> = emptyList<String>().toMutableList()
    private var _pass : MutableList<String> = emptyList<String>().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtRegist = findViewById<TextView>(R.id.txtSignUp)
        val _loginUsr = findViewById<EditText>(R.id.loginUsr)
        val _loginPass = findViewById<EditText>(R.id.regPass)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        db = FirebaseFirestore.getInstance()
        val sharedPreferences = getSharedPreferences("SessionUser", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var dbAccount = db.collection("account")

        dbAccount.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    _userN.add(document.data["username"].toString())
                    _pass.add(document.data["password"].toString())
                    Log.d("CEK DATA", "CEK ${_userN}")
                    Log.d("GET DATA", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("GET DATA", "Error getting documents: ", exception)
            }

        txtRegist.setOnClickListener {
            val toRegistIntent = Intent(this@Login, Registration::class.java)
            startActivity(toRegistIntent)
        }

        buttonLogin.setOnClickListener{
            var cek = false
            for (x in 0.._userN.size-1){
                if (_loginUsr.text.toString().equals(_userN[x].toString()) and _loginPass.text.toString().equals(_pass[x].toString())){
                    cek = true
                    Toast.makeText(
                        this@Login,
                        "Login Success!",
                        Toast.LENGTH_SHORT
                    ).show()

                    editor.putString("name", _loginUsr.text.toString())
                    editor.apply()

                    // PERLU DIGANTI!!
                    val eIntent = Intent(this@Login, tesHome::class.java)
                    startActivity(eIntent)
                }
            }

            if (!cek){
                Toast.makeText(
                    this@Login,
                    "Email atau Password Salah!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}