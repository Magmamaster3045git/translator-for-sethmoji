package com.example.sethmojitranslator

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class DictionaryEntry(
    val id: String = "",
    val english: String = "",
    val emoji: String = ""
)

class DictionaryViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _entries = MutableStateFlow<List<DictionaryEntry>>(emptyList())
    val entries: StateFlow<List<DictionaryEntry>> = _entries

    private var listener: ListenerRegistration? = null

    init {
        listenToDictionary()
    }

    private fun listenToDictionary() {
        listener = db.collection("dictionary")
            .addSnapshotListener { snapshot, _ ->
                if (snapshot == null) return@addSnapshotListener

                _entries.value = snapshot.documents.map { doc ->
                    DictionaryEntry(
                        id = doc.id,
                        english = doc.getString("english") ?: "",
                        emoji = doc.getString("emoji") ?: ""
                    )
                }
            }
    }

    fun addEntry(english: String, emoji: String) {
        val data = hashMapOf(
            "english" to english,
            "emoji" to emoji
        )

        db.collection("dictionary").add(data)
    }

    fun deleteEntry(id: String) {
        db.collection("dictionary")
            .document(id)
            .delete()
    }

    fun translate(word: String): String {
        val clean = word.trim()

        return _entries.value.find {
            it.english.equals(clean, ignoreCase = true)
        }?.emoji ?: clean
    }

    override fun onCleared() {
        listener?.remove()
        super.onCleared()
    }
}
