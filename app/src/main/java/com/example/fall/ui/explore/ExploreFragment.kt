package com.example.fall.ui.explore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.fall.R
import com.example.fall.adapterthread
import com.example.fall.databinding.FragmentExploreBinding
import com.example.fall.thread
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val exploreViewModel =
            ViewModelProvider(this).get(ExploreViewModel::class.java)

        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _loading = view.findViewById<LottieAnimationView>(R.id.loading_animationExp)
        val rvGenre = view.findViewById<RecyclerView>(R.id.recyclerViewGenre)


        // AMBIL DATA ARRAY LIST DARI DB DAN MASUKKAN KE RECYCLE VIEW
        val db = FirebaseFirestore.getInstance()
        val threadsRef = db.collection("threads").orderBy("date", Query.Direction.DESCENDING)
        var dataBundle = ArrayList<thread>()
//
        threadsRef.get()
            .addOnSuccessListener { querySnapshot ->
                // Iterate through the documents in the collection
                for (documentSnapshot in querySnapshot) {
                    // Ambil judul dari setiap document
                    val idGenre = documentSnapshot.getString("id_genre")
                    val idUser = documentSnapshot.getString("id_user")
                    val hirarki = documentSnapshot.getString("hirarki")
                    val judul = documentSnapshot.getString("judul")
                    val isi = documentSnapshot.getString("isi")
                    val like = documentSnapshot.getLong("like")
                    val dislike = documentSnapshot.getLong("dislike")
                    val date = documentSnapshot.getTimestamp("date")?.toDate()

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

                    Log.d("CEK DATA HOME", "Data: $dataBundle")
                    val rvThread = view.findViewById<RecyclerView>(R.id.rvThreadExplore)
                    rvThread.layoutManager = LinearLayoutManager(view.context)
                    val adapterRV = adapterthread(dataBundle)
                    rvThread.adapter = adapterRV

                }

                _loading.visibility = View.INVISIBLE
            }
            .addOnFailureListener { exception ->
                // Handle error
            }
    }
}