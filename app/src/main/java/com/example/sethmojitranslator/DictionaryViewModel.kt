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
        viewModelScope.launch(Dispatchers.IO) {

            // Load existing data
            val existing = dao.getAll()

            // If empty → seed default dictionary
            if (existing.isEmpty()) {
                DictionarySeeder.defaultData().forEach {
                    dao.insert(it)
                }
            }

            // Load final data into UI state
            _entries.value = dao.getAll()
        }
    }

    fun add(entry: DictionaryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(entry)
            _entries.value = dao.getAll()
        }
    }

    fun update(entry: DictionaryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(entry)
            _entries.value = dao.getAll()
        }
    }

    fun delete(entry: DictionaryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(entry)
            _entries.value = dao.getAll()
        }
    }
}
