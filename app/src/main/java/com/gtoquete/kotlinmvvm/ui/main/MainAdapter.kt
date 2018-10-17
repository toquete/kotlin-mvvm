package com.gtoquete.kotlinmvvm.ui.main

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gtoquete.kotlinmvvm.R
import com.gtoquete.kotlinmvvm.data.model.Note
import com.gtoquete.kotlinmvvm.databinding.ItemNotesListBinding
import com.gtoquete.kotlinmvvm.infrastructure.ARGUMENT_NOTE_ID
import com.gtoquete.kotlinmvvm.infrastructure.AdapterItemsContract
import com.gtoquete.kotlinmvvm.ui.editnote.EditNoteActivity
import com.gtoquete.kotlinmvvm.viewmodel.NoteItemViewModel

class MainAdapter(private val context: Context?,
                  private var notes: List<Note>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AdapterItemsContract {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemNotesListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_notes_list, parent, false)

        return BindingHolder(binding)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BindingHolder).bind(notes[position])
    }

    override fun replaceItems(items: List<*>) {
        this.notes = items as List<Note>
        notifyDataSetChanged()
    }

    inner class BindingHolder(private val binding: ItemNotesListBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var viewModel: NoteItemViewModel

        fun bind(note: Note) {
            viewModel = NoteItemViewModel()
            viewModel.note.set(note)
            binding.note = note
            binding.cardviewNote.setOnClickListener { viewModel.onCardClick { goToAddEditNoteScreen(it) } }
            binding.executePendingBindings()
        }
    }

    private fun goToAddEditNoteScreen(noteId: String?) {
        Intent(context, EditNoteActivity::class.java).apply {
            putExtra(ARGUMENT_NOTE_ID, noteId)
            context?.startActivity(this)
        }
    }
}