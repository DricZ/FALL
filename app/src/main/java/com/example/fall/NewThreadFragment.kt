package com.example.fall

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class NewThreadFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_thread, container, false)
    }
    var listThread : ArrayList<thread> = arrayListOf<thread>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val img = view.findViewById<ImageView>(R.id.imageView11)

//        // access the items of the list
//        val genre = resources.getStringArray(R.array.genre)
//        val genre2 = arrayOf("satu", "dua")
//
//        // access the spinner
        val spinner = view.findViewById<Spinner>(R.id.spinner)
//        if (spinner != null) {
//            spinner.adapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, genre2)
////            var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,genre)
//        }

        ArrayAdapter.createFromResource(
            view.context,
            R.array.genre,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val btnPost = view.findViewById<Button>(R.id.buttonPostThread)
        val etTilte = view.findViewById<EditText>(R.id.titleNewThread)
        val etIsi = view.findViewById<EditText>(R.id.isiNewThread)

        btnPost.setOnClickListener{
            val newThread = thread(etTilte.text.toString(), etIsi.text.toString(), 0, 0)
            listThread.add(newThread)
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
                putExtra("DATA", listThread)
            }
            startActivity(eIntent)
        }


    }

}