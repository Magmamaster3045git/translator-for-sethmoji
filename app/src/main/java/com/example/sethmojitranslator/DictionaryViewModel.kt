package com.example.sethmojitranslator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DictionaryViewModel(
    private val dao: DictionaryDao
) : ViewModel() {

    private val _entries = MutableStateFlow<List<DictionaryEntity>>(emptyList())
    val entries: StateFlow<List<DictionaryEntity>> = _entries

    init {
        load()
    }

    private fun load() {
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
