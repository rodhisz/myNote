package com.example.mynote.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val note : String
)
