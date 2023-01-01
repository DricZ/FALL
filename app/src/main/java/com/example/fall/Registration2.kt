package com.example.fall

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.*
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.FirebaseException
import java.util.*
import java.util.concurrent.TimeUnit

class Registration2 : AppCompatActivity() {
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration2)

        // DB INIT
        db = FirebaseFirestore.getInstance()

        val imgShuffle = findViewById<ImageView>(R.id.imageView3)
        val imgShuffleSex = findViewById<ImageView>(R.id.imageView4)
        var etNn = findViewById<EditText>(R.id.regNickname)
        var tvSex = findViewById<TextView>(R.id.regSex)
        val etPhone = findViewById<EditText>(R.id.regPhone)
        val btnRegistn = findViewById<Button>(R.id.buttonRegist)

        imgShuffle.setOnClickListener {
            randomNickName()
//            etNn.setText("")
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

        //MASUKKAN DATA USER KE FIREBASE
        btnRegistn.setOnClickListener {
            val otp = generateOTP()
            Log.d("OTP", otp)
            //TAMBAHKAN PENGECEKAN HASIL RUNDOM KALO SUDAH PERNAH MAKA AKAN DI PANGGIL ULANG generateOTP()
            val MY_PERMISSIONS_REQUEST_SEND_SMS = 123
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
                // Belum mendapat izin
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.SEND_SMS),
                    MY_PERMISSIONS_REQUEST_SEND_SMS)
            } else {
                // Telah mendapat izin
                // Lakukan proses mengirim SMS di sini
                val smsManager = SmsManager.getDefault()
                val phoneNumber = etPhone.text.toString() // ganti dengan nomor telepon Anda
                val message = "[FALL] Kode OTP anda adalah $otp, mohon untuk tidak memberitahukan kepada siapapun!!!" // ganti dengan kode OTP yang ingin Anda kirim
                smsManager.sendTextMessage(phoneNumber, null, message, null, null)
                startActivity(Intent(this@Registration2, OtpActivity::class.java).apply {
                    putExtra("OTP", otp)
                    putExtra("noHp", phoneNumber)
                })
            }
        }

        val getOtp = intent.getStringExtra("TRY")
        Log.d("NEW OTP", getOtp.toString())
//        if (getOtp == "again"){
//            val otp = generateOTP()
//            Log.d("OTP", otp)
//            //TAMBAHKAN PENGECEKAN HASIL RUNDOM KALO SUDAH PERNAH MAKA AKAN DI PANGGIL ULANG generateOTP()
//            val MY_PERMISSIONS_REQUEST_SEND_SMS = 123
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
//                != PackageManager.PERMISSION_GRANTED) {
//                // Belum mendapat izin
//                ActivityCompat.requestPermissions(this,
//                    arrayOf(Manifest.permission.SEND_SMS),
//                    MY_PERMISSIONS_REQUEST_SEND_SMS)
//            } else {
//                // Telah mendapat izin
//                // Lakukan proses mengirim SMS di sini
//                val smsManager = SmsManager.getDefault()
//                val phoneNumber = etPhone.text.toString() // ganti dengan nomor telepon Anda
//                val message = "[FALL] Kode OTP anda adalah $otp, mohon untuk tidak memberitahukan kepada siapapun!!!" // ganti dengan kode OTP yang ingin Anda kirim
//                smsManager.sendTextMessage(phoneNumber, null, message, null, null)
//                startActivity(Intent(this@Registration2, OtpActivity::class.java).apply {
//                    putExtra("OTP", otp)
//                    putExtra("noHp", phoneNumber)
//                })
//            }
//        }

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


    fun generateOTP(): String {
        val random = Random()
        val num = random.nextInt(900000) + 100000
        return num.toString()
    }

}