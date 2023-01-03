package com.example.fall

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class adapterthread(
    private val listThread: ArrayList<thread>?
) : RecyclerView.Adapter<adapterthread.ListViewHolder>(){

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var _judulThread :TextView = itemView.findViewById(R.id.tJudul)
        var _isiThread : TextView = itemView.findViewById(R.id.tIsi)
        var _totLike : TextView = itemView.findViewById(R.id.tLike)
        var _totDis : TextView = itemView.findViewById(R.id.tDis)
        val _dateTh: TextView = itemView.findViewById(R.id.dateTh)
        val _cardThread: View = itemView.findViewById(R.id.cardThread)
        val _comment: ImageView = itemView.findViewById(R.id.imageViewComment)
//               var _totalThumbsUp : ImageView = itemView.findViewById(R.id.imageView9)
//               var _totalThumbsDown : ImageView = itemView.findViewById(R.id.imageView10)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback
    interface OnItemClickCallback{
        fun editThread(pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.threaditem, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var thread = listThread!!.get(position)

//        Log.d("THREAD_DATA", "Judul: ${thread.judul}, Like: ${thread.like}, Dislike: ${thread.dislike}")

        holder._judulThread.setText(thread.judul)
        holder._isiThread.setText(thread.isi)
        holder._totLike.setText(thread.like.toString())
        holder._totDis.setText(thread.dislike.toString())
        holder._dateTh.setText(thread.date.toString())
        val db = FirebaseFirestore.getInstance()
//        Log.d("JUDUL", thread.judul?)
        holder._cardThread.setOnClickListener {
//            onItemClickCallback.editThread(position)
//            Log.d("WOE", "PLIS")
            val colRef = db.collection("threads").whereEqualTo("judul", thread.judul).whereEqualTo("isi",thread.isi).whereEqualTo("date", thread.date)
            colRef.get()
                .addOnSuccessListener { anjer ->
                    for (anjers in anjer){
                        val id = anjers.id
                        holder._cardThread.context.startActivity(Intent(holder._cardThread.context, CommentActivity::class.java).apply {
                            putExtra("THREADS", id)
                        })
                    }
                }
        }

        holder._comment.setOnClickListener {
//            onItemClickCallback.editThread(position)
//            Log.d("WOE", "PLIS")
            val colRef = db.collection("threads").whereEqualTo("judul", thread.judul).whereEqualTo("isi",thread.isi).whereEqualTo("date", thread.date)
            colRef.get()
                .addOnSuccessListener { anjer ->
                    for (anjers in anjer){
                        val id = anjers.id
                        holder._cardThread.context.startActivity(Intent(holder._cardThread.context, AddCommentActivity::class.java).apply {
                            putExtra("THREADS", id)
                        })
                    }
                }
        }
    }

    override fun getItemCount(): Int {
        return listThread!!.size
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}