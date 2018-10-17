package com.gtoquete.kotlinmvvm.viewmodel

import android.databinding.ObservableArrayList
import com.gtoquete.kotlinmvvm.data.model.Note
import com.gtoquete.kotlinmvvm.mock.NotesMockHelper
import java.util.UUID

class MainViewModel {
    val notes = ObservableArrayList<Note>()


    fun load(showNotes: (noteList: List<Note>) -> Unit) {
        if (NotesMockHelper.notesMockList.isEmpty()) {
            val notesList = listOf(Note(UUID.randomUUID().toString(), "Título", "Isso é um teste"))
            NotesMockHelper.notesMockList.addAll(notesList)
        }

        notes.clear()
        notes.addAll(NotesMockHelper.notesMockList)
        showNotes(notes)
    }
}