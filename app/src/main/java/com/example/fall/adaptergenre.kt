package com.example.fall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adaptergenre(
    private val listGenre: ArrayList<genre>?): RecyclerView.Adapter<adaptergenre.ListViewHolder>() {
        inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            var _namaGenre: TextView = itemView.findViewById(R.id.textViewGenre)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.genreitem, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var genre = listGenre!!.get(position)

        holder._namaGenre.setText(genre.namaGenre)
    }

    override fun getItemCount(): Int {
        return listGenre!!.size
    }
}