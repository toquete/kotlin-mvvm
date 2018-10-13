package com.gtoquete.kotlinmvvm.presentation.editnote

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
import com.gtoquete.kotlinmvvm.infrastructure.ARGUMENT_NOTE

class EditNoteFragment : Fragment() {

    private lateinit var binding: FragmentEditNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_note, container, false)
        binding.note = activity?.intent?.getParcelableExtra(ARGUMENT_NOTE)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_edit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_save -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}