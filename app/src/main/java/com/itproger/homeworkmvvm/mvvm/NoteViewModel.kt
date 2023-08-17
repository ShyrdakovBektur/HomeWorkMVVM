package com.itproger.homeworkmvvm.mvvm

import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {
    val noteList = mutableListOf<NoteModel>()
    fun addNote(note: NoteModel) {
        noteList.add(note)
    }

    fun toggleNote(note: NoteModel) {
        note.isCompleted = !note.isCompleted
    }

    fun removeNote(note: NoteModel) {
        noteList.remove(note)
    }
}