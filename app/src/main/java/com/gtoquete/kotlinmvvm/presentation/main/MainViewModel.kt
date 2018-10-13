package com.gtoquete.kotlinmvvm.presentation.main

import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.view.View
import com.gtoquete.kotlinmvvm.data.model.Note
import com.gtoquete.kotlinmvvm.infrastructure.ARGUMENT_NOTE
import com.gtoquete.kotlinmvvm.mock.NotesMockHelper
import com.gtoquete.kotlinmvvm.presentation.editnote.EditNoteActivity

class MainViewModel : ViewModel() {
    val notes = ObservableArrayList<Note>()


    fun load() {
        notes.addAll(NotesMockHelper.notesMockList)
    }
}