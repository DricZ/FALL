package com.example.fall

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class NewThreadFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    var listThread : ArrayList<thread> = arrayListOf<thread>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnPost = view.findViewById<Button>(R.id.buttonPostThread)
        val etTilte = view.findViewById<EditText>(R.id.titleNewThread)
        val etIsi = view.findViewById<EditText>(R.id.isiNewThread)


        val calendar = Calendar.getInstance()

        // Mendapatkan waktu sekarang dalam bentuk tanggal
        val currentDate = calendar.time

        // INISIALISASI FIRESTORE
        val db = FirebaseFirestore.getInstance()
        val sharedPreferences = view.context.getSharedPreferences("SessionUser", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("name", "")


        // access the spinner
        val spinnerValues = mutableListOf<String?>()
        val spinner = view.findViewById<Spinner>(R.id.spinner)

        val hint = "Silahkan Pilih Genre"
        spinnerValues.add(hint)

        // Tentukan referensi collection yang akan digunakan
        val colRef = db.collection("genre")

        // Mengambil semua document dari collection
        colRef.get()
            .addOnSuccessListener { querySnapshot ->
                // Iterate through the documents in the collection
                for (documentSnapshot in querySnapshot) {
                    // Ambil nama dari setiap document
                    val name = documentSnapshot.getString("name")
                    spinnerValues.add(name)
                }
            }
            .addOnFailureListener { exception ->
                // Handle error
            }

        val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, spinnerValues)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinner.adapter = adapter

        // Set hint sebagai item yang dipilih pertama kali di spinner
        spinner.setSelection(adapter.getPosition(hint))

        val thr = HashMap<String, Any>()
        var genre = ""

        // Buat listener untuk menangkap item yang dipilih di spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Ambil value yang dipilih dari spinner
                genre = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d("CEK GENRE", "genr2e")
                // Do nothing
            }
        }



        btnPost.setOnClickListener{
            val title = etTilte.text.toString()
            val isi = etIsi.text.toString()


            Log.d("CEK GENRE", genre)
            thr["date"] = currentDate
            thr["id_genre"] = genre
            thr["id_user"] = savedName.toString()
            thr["hirarki"] = 0
            thr["judul"] = title
            thr["isi"] = isi
            thr["like"] = 0
            thr["dislike"] = 0

            // Tentukan referensi dokumen yang akan digunakan
            val docRef = db.collection("threads").document("thread1")

            // Mengambil dokumen dari Firestore
            docRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    // Cek apakah dokumen sudah ada di Firestore
                    if (!documentSnapshot.exists()) {

                        db.collection("threads").add(thr)
                            .addOnSuccessListener { documentReference ->
                                Log.d("CEK GENRE", "DocumentSnapshot added with ID: ${documentReference.id}")
                            }
                            .addOnFailureListener { e ->
                                Log.w("CEK GENRE", "Error adding document", e)
                            }

                        Log.d("CEK GENRE", "genre")

                        // Jika tidak ada, masukkan dokumen ke Firestore
//                        UNTUK KE LIST THREAD
//                        val thread1 = thread(thr["id_genre"].toString(),
//                                thr["id_user"].toString(),
//                                thr["hirarki"].toString(),
//                                thr["judul"].toString(),
//                                thr["isi"].toString(),
//                                thr["like"] as Int,
//                                thr["dislike"] as Int,
//                                currentDate
//                        )
//                        listThread.add(thread1)
                    }
                }
                .addOnFailureListener { exception ->
                    // Handle error
                }
//            val newThread = thread(etTilte.text.toString(), etIsi.text.toString(), 0, 0)

//            val mBundle = Bundle()
//            mBundle.putParcelableArray("DATA", listThread)
//            mBundle.putParcelableArrayList("DATA",listThread)
//
//            val mfThread = ThreadFragment()
//            mfThread.arguments = mBundle
//
//            val mFragmentManager = parentFragmentManager
//            mFragmentManager.beginTransaction().apply {
//                add(R.id.frameContainer, mfThread, ThreadFragment::class.java.simpleName)
//                addToBackStack(null)
//                commit()
//            }
            val eIntent = Intent(view.context, tesHome::class.java).apply {
//                putExtra("DATA", listThread)
            }

//            Log.d("CEK LIST DATA", listThread.toString())
            startActivity(eIntent)
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_thread, container, false)
    }




}