package com.example.week7_sec3_noteappfragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.week7_sec3_noteappfragments.modles.NoteDataBase
import com.example.week7_sec3_noteappfragments.modles.Notes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyViewModel(activity: Application): AndroidViewModel(activity) {

    private var notes1: LiveData<List<Notes>>
    val myDBRoom = NoteDataBase.getInstance(activity).NoteDao()

    init {

        notes1 = myDBRoom.getAllNotes()

    }

    fun get_Note(): LiveData<List<Notes>> {
        return notes1
    }

    fun add_Note(n: String) {
        CoroutineScope(Dispatchers.IO).launch {

            myDBRoom.insertNote(Notes(0,n))
            notes1 = myDBRoom.getAllNotes()
        }
    }

    fun update_Note(id: Int, n: String) {
        CoroutineScope(Dispatchers.IO).launch {
            myDBRoom.updateNote(Notes(id, n))
            notes1 = myDBRoom.getAllNotes()
        }
    }

    fun delete_Note(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            myDBRoom.deleteNote(Notes(id,""))
            notes1 = myDBRoom.getAllNotes()
        }
    }


}