package com.gtoquete.kotlinmvvm.viewmodel

import android.databinding.ObservableField
import com.gtoquete.kotlinmvvm.data.model.Note

class NoteItemViewModel {
    val note = ObservableField<Note>()

    fun onCardClick(showEditNoteScreen: (noteId: String?) -> Unit) {
        showEditNoteScreen(note.get()?.id)
    }
}