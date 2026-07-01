package com.example.sethmojitranslator

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class DictionaryEntry(
    val id: Int,
    var english: String,
    var emoji: String
)

class DictionaryViewModel : ViewModel() {

    private val _entries = mutableStateListOf<DictionaryEntry>()
    val entries: List<DictionaryEntry> = _entries

    init {
        _entries.addAll(
            listOf(
                DictionaryEntry(1, "Hello", "👋"),
                DictionaryEntry(2, "Love", "💛"),
                DictionaryEntry(3, "Yes", "👍🏿"),
                DictionaryEntry(4, "No", "👎🏼")
            )
        )
    }

    fun addEntry(english: String, emoji: String) {
        val newId = (_entries.maxOfOrNull { it.id } ?: 0) + 1
        _entries.add(DictionaryEntry(newId, english, emoji))
    }

    fun deleteEntry(id: Int) {
        _entries.removeAll { it.id == id }
    }

    fun translate(word: String): String {
        return _entries.find {
            it.english.equals(word, ignoreCase = true)
        }?.emoji ?: word
    }
} 
