package com.example.fall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapterthread (
    private val listThread: ArrayList<thread>
        ) : RecyclerView.Adapter<adapterthread.ListViewHolder>(){

           inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
               var _judulThread :TextView = itemView.findViewById(R.id.textViewItemUsername)
               var _isiThread : TextView = itemView.findViewById(R.id.textViewItemIsi)
//               var _totalThumbsUp : ImageView = itemView.findViewById(R.id.imageView9)
//               var _totalThumbsDown : ImageView = itemView.findViewById(R.id.imageView10)
           }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.threaditem, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var thread = listThread[position]

        holder._judulThread.setText(thread.judul)
        holder._isiThread.setText(thread.isiThread)

    }

    override fun getItemCount(): Int {
        return listThread.size
    }
}