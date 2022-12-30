package com.example.fall.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fall.*
import com.example.fall.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    private lateinit var _judul: Array<String>
    private lateinit var _isi : Array<String>

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAddThread = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        btnAddThread.setOnClickListener {
            val intent = Intent(view.context, MainActivityFragment::class.java)
            startActivity(intent)
        }

        // AMBIL DATA ARRAY LIST DARI DB DAN MASUKKAN KE RECYCLE VIEW
//        var dataBundle = ArrayList<thread>()
//        if (arguments != null){
//            dataBundle = arguments?.getParcelableArrayList<thread>("DATA") as ArrayList<thread>
//            Log.d("DATA_BUNDLE", dataBundle.toString())
//
//        }
//        val rvThread = view.findViewById<RecyclerView>(R.id.recyclerView)
//        rvThread.layoutManager = LinearLayoutManager(view.context)
//        val adapterRV = adapterthread(dataBundle)
//        rvThread.adapter = adapterRV

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}