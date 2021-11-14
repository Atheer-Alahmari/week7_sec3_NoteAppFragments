package com.example.week7_sec3_noteappfragments.modles

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {
    @Query("SELECT * FROM Massege /* ORDER BY Note DESC*/")
    fun getAllNotes(): LiveData<List<Notes>>

    @Insert
    fun insertNote(note1:Notes)

    @Delete
    fun deleteNote(id: Notes)

    @Update
    fun updateNote(id: Notes)

}
