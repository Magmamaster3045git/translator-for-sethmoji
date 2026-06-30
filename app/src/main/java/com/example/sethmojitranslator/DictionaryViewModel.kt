package com.example.sethmojitranslator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DictionaryViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).dictionaryDao()

    private val _entries = MutableStateFlow<List<DictionaryEntity>>(emptyList())
    val entries: StateFlow<List<DictionaryEntity>> = _entries

    init {
        load()
    }

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            _entries.value = dao.getAll()
        }
    }

    fun add(entry: DictionaryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(entry)
            load()
        }
    }

    fun update(entry: DictionaryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(entry)
            load()
        }
    }

    fun delete(entry: DictionaryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(entry)
            load()
        }
    }
}
