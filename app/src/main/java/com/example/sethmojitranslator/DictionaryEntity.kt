package com.example.sethmojitranslator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class DictionaryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val english: String,
    val emoji: String
)
