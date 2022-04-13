package com.example.mynote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.room.Note
import kotlinx.android.synthetic.main.adapter_note.view.*

class NoteAdapter (private val notes : ArrayList<Note>,private val listener : onAdapterListener)
     : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_note,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.view.text_title.text = note.title

        //coba cek id pake toast
        holder.view.text_title.setOnClickListener {
            listener.onClick( note )
        }
    }

    override fun getItemCount() = notes.size

    class NoteViewHolder (val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list : List<Note>) {
        notes.clear()
        notes.addAll(list)
        notifyDataSetChanged()
    }

    interface onAdapterListener {
        fun onClick(note: Note)
    }


}