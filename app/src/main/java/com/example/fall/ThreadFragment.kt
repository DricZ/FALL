package com.example.fall

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ThreadFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAddThread = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        btnAddThread.setOnClickListener{
            val mfExp = NewThreadFragment()
            val mFragment = parentFragmentManager
            mFragment.beginTransaction().apply {
                replace(R.id.frameContainer, mfExp, NewThreadFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        var dataBundle = ArrayList<thread>()
        if (arguments != null){
            dataBundle = arguments?.getParcelableArrayList<thread>("DATA")!!
            Log.d("DATA_BUNDLE", dataBundle.toString())

        }
        val rvThread = view.findViewById<RecyclerView>(R.id.recyclerView)
        rvThread.layoutManager = LinearLayoutManager(view.context)
        val adapterRV = adapterthread(dataBundle)
        rvThread.adapter = adapterRV
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thread, container, false)
    }

}