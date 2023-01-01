package com.example.fall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class Registration2 : AppCompatActivity() {
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration2)

        // DB INIT
        db = FirebaseFirestore.getInstance()

        val imgShuffle = findViewById<ImageView>(R.id.imageView3)
        val imgShuffleSex = findViewById<ImageView>(R.id.imageView4)
        val regNickname = findViewById<EditText>(R.id.regNickname)
        val regSex = findViewById<TextView>(R.id.regSex)
        val buttonRegister = findViewById<Button>(R.id.buttonRegist)

//      INIT AGE DI REG 2
        val getUser = intent.getStringExtra("user")
        val getPass = intent.getStringExtra("pass")
        val getAge = intent.getStringExtra("age")

        imgShuffle.setOnClickListener {
            randomNickName()
            regNickname.setText("")
            regNickname.setText(randomNickName())
        }
        imgShuffleSex.setOnClickListener {
            while (true){
                var hasilRandomSex = randomSex()
                if (hasilRandomSex == regSex.text.toString()){
                    hasilRandomSex = randomSex()
                    continue
                }else{
                    regSex.setText("")
                    regSex.setText(hasilRandomSex)
                    break
                }
            }
        }

        buttonRegister.setOnClickListener {
            TambahData(regNickname.text.toString(), getUser.toString(), getPass.toString(), getAge.toString().toInt(), regSex.text.toString())

            Toast.makeText(
                this@Registration2,
                "Register Success!",
                Toast.LENGTH_SHORT
            ).show()

            val eIntent = Intent(this@Registration2, Login::class.java)
            startActivity(eIntent)
        }
    }

    fun randomNickName(): String {
        val listNick = mutableListOf("Juragan Geprek", "Rujak Impor", "Cimol Impor", "Kuli Bitcoin", "Atlit Pargoi", "Kamu Nanyea", "Hamba Elon", "Bestie Palsu", "Macha Goreng", "Bakso Geprek", "Atlit Selfie", "Camat Texas", "Pocong Gabut", "Satpam Hatimu", "Kuli Diamond", "Master Peace", "Bupati Miranmar", "Gubernur Land of Down", "TopGlobal Layla", "Peneliti Anime", "Pepatah Kongo", "Sarjana Mager", "Pepatah Zimbabwe", "Joki Tidur", "Pocong Breakdance")
//        Log.d("PANJANG LIST", listNick.size.toString())
        val randomElement = listNick.asSequence().shuffled().find { true }
//        Log.d("Nick", randomElement.toString())
        return randomElement.toString()
    }

    fun randomSex(): String{
        val sex = listOf("Male", "Female")
        val randomElement = sex.asSequence().shuffled().find { true }
        return randomElement.toString()
    }

    private fun TambahData(nickname: String, username: String, password: String, age: Int, sex: String) {
        val user = hashMapOf(
            "nickname" to nickname,
            "username" to username,
            "password" to password,
            "age" to age,
            "sex" to sex
        )

        db.collection("account").add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("CEK REGISTER", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("CEK REGISTER", "Error adding document", e)
            }
    }
}