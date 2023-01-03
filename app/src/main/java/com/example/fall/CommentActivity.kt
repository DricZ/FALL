package com.example.fall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class CommentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        val _tJudul = findViewById<TextView>(R.id.tJudul)
        val _tIsi = findViewById<TextView>(R.id.tIsi)
        val _tLike = findViewById<TextView>(R.id.tLike)
        val _tDis = findViewById<TextView>(R.id.tDis)


        val db = FirebaseFirestore.getInstance()
//        Log.d("JUDUL", thread.judul?)
        val getThread = intent.getStringExtra("THREADS")
//        val listView = findViewById<ListView>(R.id.listviewComment)
//        val adapter = ArrayAdapter<thread>(this, android.R.layout.simple_list_item_1)
//        listView.adapter = adapter

        val threadsRef = db.collection("threads")
        val docRef = threadsRef.document(getThread.toString())
        docRef.get()
            .addOnSuccessListener { anjer ->
                    val idGenre = anjer.getString("id_genre")
                    val idUser = anjer.getString("id_user")
                    val judul = anjer.getString("judul")
                    val isi = anjer.getString("isi")
                    val like = anjer.getLong("like")
                    val dislike = anjer.getLong("dislike")
                    val date = anjer.getTimestamp("date")?.toDate()

                    _tJudul.setText(judul)
                    _tIsi.setText(isi)
                    _tLike.setText(like.toString())
                    _tDis.setText(dislike.toString())

                    Log.d("CEK JUDUL C", _tJudul.text.toString())
                }



        val colRef = db.collection("threads").whereEqualTo("hirarki", getThread).orderBy("date", Query.Direction.DESCENDING)
        var dataBundle = ArrayList<thread>()
        colRef.get()
            .addOnSuccessListener { anjer ->
                for (anjers in anjer) {
                    val idGenre = anjers.getString("id_genre")
                    val idUser = anjers.getString("id_user")
                    val hirarki = anjers.getString("hirarki")
                    val judul = anjers.getString("judul")
                    val isi = anjers.getString("isi")
                    val like = anjers.getLong("like")
                    val dislike = anjers.getLong("dislike")
                    val date = anjers.getTimestamp("date")?.toDate()


//                    adapter.add(date?.let {
//                        thread(idGenre, idUser, hirarki, judul, isi, like, dislike,
//                            it
//                        )
//                    })
//                    adapter.notifyDataSetChanged()
                    // Tambahkan data ke dalam list
                    val newThread =
                        date?.let {
                            thread(idGenre, idUser, hirarki, judul, isi, like, dislike,
                                it
                            )
                        }

                    if (newThread != null) {
                        dataBundle.add(newThread)
                    }

                    Log.d("CEK DATA COMMENT", "Data: $dataBundle")
                    val rvThread = findViewById<RecyclerView>(R.id.rvComment)
                    rvThread.layoutManager = LinearLayoutManager(this)
                    val adapterRV = adapterthread(dataBundle)
                    rvThread.adapter = adapterRV

                }

            }



        Log.d("ID THREADS", getThread.toString())

    }
}