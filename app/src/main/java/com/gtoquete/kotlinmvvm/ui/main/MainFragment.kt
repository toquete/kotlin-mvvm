package com.gtoquete.kotlinmvvm.ui.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
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
import com.gtoquete.kotlinmvvm.infrastructure.ARGUMENT_NOTE_ID
import com.gtoquete.kotlinmvvm.ui.editnote.EditNoteActivity
import com.gtoquete.kotlinmvvm.viewmodel.MainViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel: MainViewModel

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

        viewModel = MainViewModel()
        binding.viewModel = viewModel
        binding.fab.setOnClickListener { goToAddEditNoteScreen() }
    }

    override fun onStart() {
        super.onStart()
        viewModel.load { showNotesList(it) }
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

    private fun showNotesList(notesList: List<Note>) {
        binding.apply {
            recyclerviewNotes.layoutManager = LinearLayoutManager(context)
            recyclerviewNotes.adapter = MainAdapter(context, notesList)
        }
    }

    private fun goToAddEditNoteScreen() {
        Intent(context, EditNoteActivity::class.java).apply {
            putExtra(ARGUMENT_NOTE_ID, EditNoteActivity.REQUEST_CODE_NEW_NOTE)
            startActivity(this)
        }
    }

}
