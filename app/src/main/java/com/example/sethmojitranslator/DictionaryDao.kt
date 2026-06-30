package com.example.sethmojitranslator

import androidx.room.*

@Dao
interface DictionaryDao {

    @Query("SELECT * FROM dictionary")
    fun getAll(): List<DictionaryEntity>

    @Insert
    fun insert(entry: DictionaryEntity)

    @Update
    fun update(entry: DictionaryEntity)

    @Delete
    fun delete(entry: DictionaryEntity)

    @Query("DELETE FROM dictionary")
    fun clearAll()
}
