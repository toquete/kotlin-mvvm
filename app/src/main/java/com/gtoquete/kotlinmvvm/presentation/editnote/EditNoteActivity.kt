package com.gtoquete.kotlinmvvm.presentation.editnote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gtoquete.kotlinmvvm.R
import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        setSupportActionBar(toolbar)
    }

}