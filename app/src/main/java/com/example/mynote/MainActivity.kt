package com.example.mynote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynote.room.Constant
import com.example.mynote.room.Note
import com.example.mynote.room.NoteDB
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { NoteDB(this) }
    lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNotes()
            Log.d("MainActivity", "dbResponse : $notes")
            withContext(Dispatchers.Main){
                noteAdapter.setData( notes )
            }
        }
    }

    fun setupListener() {
        button_create.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        noteAdapter = NoteAdapter(arrayListOf(), object : NoteAdapter.onAdapterListener{

            //onclick dari adapter
            override fun onClick(note: Note) {
//                Toast.makeText(applicationContext, note.title, Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(applicationContext, EditActivity::class.java)
                        .putExtra("intent_id", note.id)
                )
            }

        })
        list_note.apply {
            layoutManager = LinearLayoutManager(applicationContext )
            adapter = noteAdapter
        }
    }

    private fun intentEdit(noteId: Int, intentType:Int){
        startActivity(Intent(this, EditActivity::class.java)
            .putExtra( "note_id", noteId)
            .putExtra( "intentType", intentType)
        )
    }
}