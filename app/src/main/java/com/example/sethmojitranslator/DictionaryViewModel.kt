package com.example.sethmojitranslator

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class DictionaryViewModel : ViewModel() {

    private val _entries = mutableStateListOf<DictionaryEntry>()
    val entries: List<DictionaryEntry> = _entries

    init {
        _entries.addAll(SethmojiDictionary.entries)
    }

    fun addEntry(english: String, emoji: String) {
        _entries.add(
            DictionaryEntry(
                english = english,
                emoji = emoji
            )
        )
    }

    fun deleteEntry(entry: DictionaryEntry) {
        _entries.remove(entry)
    }

    fun translate(word: String): String {
        return _entries.firstOrNull {
            it.english.equals(word, ignoreCase = true)
        }?.emoji ?: word
    }

    fun translateEmoji(emoji: String): String {
        return _entries.find {
            it.emoji == emoji.trim()
        }?.english ?: emoji
    }
}
