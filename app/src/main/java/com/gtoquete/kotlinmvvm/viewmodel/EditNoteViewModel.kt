package com.gtoquete.kotlinmvvm.viewmodel

import android.databinding.ObservableField
import com.gtoquete.kotlinmvvm.data.model.Note
import com.gtoquete.kotlinmvvm.mock.NotesMockHelper
import java.util.UUID

class EditNoteViewModel {
    val title = ObservableField<String>()
    val content = ObservableField<String>()
    val note = ObservableField<Note>()

    private var isNewNote = false
    private var noteId: String? = ""

    fun load(noteId: String?) {
        this.noteId = noteId
        val notes = NotesMockHelper.notesMockList.filter { it.id == noteId }
        if (notes.isEmpty()) {
            isNewNote = true
        } else {
            isNewNote = false
            title.set(notes[0].title)
            content.set(notes[0].content)
        }

    }

    fun onSaveNote(onNoteSaved: () -> Unit) {
        if (isNewNote) {
            createNote()
        } else {
            saveNote()
        }

        onNoteSaved()
    }

    private fun createNote() {
        NotesMockHelper.notesMockList.add(Note(UUID.randomUUID().toString(), title.get(),
                content.get()))
    }

    private fun saveNote() {
        val note = NotesMockHelper.notesMockList.filter { it.id == noteId }[0]
        NotesMockHelper.notesMockList[NotesMockHelper.notesMockList.indexOf(note)] =
                Note(noteId, title.get(), content.get())
    }
}