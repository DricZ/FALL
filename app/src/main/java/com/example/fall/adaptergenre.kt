package com.example.fall.ui.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fall.R
import com.example.fall.genre

class adaptergenre(private val data: List<genre>) : RecyclerView.Adapter<adaptergenre.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(id: String)
    }

    private lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: genre) {
            val _tvGenre = itemView.findViewById<TextView>(R.id.textViewGenre)
            _tvGenre.setText(data.namaGenre.toString())

            itemView.setOnClickListener {
                listener.onItemClicked(data.namaGenre.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genreitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
