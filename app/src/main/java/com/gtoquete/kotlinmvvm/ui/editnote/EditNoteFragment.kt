package com.gtoquete.kotlinmvvm.ui.editnote

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.gtoquete.kotlinmvvm.R
import com.gtoquete.kotlinmvvm.databinding.FragmentEditNoteBinding
import com.gtoquete.kotlinmvvm.infrastructure.ARGUMENT_NOTE_ID
import com.gtoquete.kotlinmvvm.viewmodel.EditNoteViewModel

class EditNoteFragment : Fragment() {

    private lateinit var binding: FragmentEditNoteBinding

    private lateinit var viewModel: EditNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_note, container, false)
        viewModel = EditNoteViewModel()
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.load(activity?.intent?.getStringExtra(ARGUMENT_NOTE_ID))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_edit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_save -> {
                viewModel.onSaveNote { onNoteSaved() }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onNoteSaved() {
        activity?.setResult(Activity.RESULT_OK)
        activity?.finish()
    }

}