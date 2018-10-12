package com.gtoquete.kotlinmvvm.presentation.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.gtoquete.kotlinmvvm.R
import com.gtoquete.kotlinmvvm.data.model.Note
import com.gtoquete.kotlinmvvm.databinding.FragmentMainBinding
import com.gtoquete.kotlinmvvm.infrastructure.ARGUMENT_NOTE
import com.gtoquete.kotlinmvvm.mock.NotesMockHelper
import com.gtoquete.kotlinmvvm.presentation.editnote.EditNoteActivity

/**
 * A placeholder fragment containing a simple view.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewNotes.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewNotes.adapter = MainAdapter(NotesMockHelper.notesMockList, object : MainAdapter.OnCardClickListener {
            override fun onCardClick(note: Note) {
                goToEditScreen(note)
            }
        })

        binding.fab.setOnClickListener { goToEditScreen(Note()) }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToEditScreen(note: Note) {
        Intent(context, EditNoteActivity::class.java).apply {
            putExtra(ARGUMENT_NOTE, note)
            startActivity(this)
        }
    }
}
