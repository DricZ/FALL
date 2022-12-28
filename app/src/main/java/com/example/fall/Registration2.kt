package com.example.fall

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class Registration2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration2)
        val imgShuffle = findViewById<ImageView>(R.id.imageView3)
        val imgShuffleSex = findViewById<ImageView>(R.id.imageView4)
        var etNn = findViewById<EditText>(R.id.regAge)
        var tvSex = findViewById<TextView>(R.id.textViewSex)

        imgShuffle.setOnClickListener {
            randomNickName()
            etNn.setText("")
            etNn.setText(randomNickName())
        }
        imgShuffleSex.setOnClickListener {
            while (true){
                var hasilRandomSex = randomSex()
                if (hasilRandomSex == tvSex.text.toString()){
                    hasilRandomSex = randomSex()
                    continue
                }else{
                    tvSex.setText("")
                    tvSex.setText(hasilRandomSex)
                    break
                }
            }


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
}