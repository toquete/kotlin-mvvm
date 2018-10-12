package com.gtoquete.kotlinmvvm.presentation.main

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gtoquete.kotlinmvvm.R
import com.gtoquete.kotlinmvvm.data.model.Note
import com.gtoquete.kotlinmvvm.databinding.ItemNotesListBinding

class MainAdapter(private val notes: List<Note>,
                  private val onCardClickListener: OnCardClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemNotesListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_notes_list, parent, false)

        return BindingHolder(binding)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BindingHolder).bind(notes[position], holder)
    }

    inner class BindingHolder(private val binding: ItemNotesListBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = MainViewModel()

        fun bind(note: Note, viewHolder: RecyclerView.ViewHolder) {
            viewModel.bind(note)
            binding.cardviewNote.setOnClickListener { onCardClickListener.onCardClick(notes[viewHolder.adapterPosition]) }
            binding.viewModel = viewModel
        }
    }

    interface OnCardClickListener {
        fun onCardClick(note: Note)
    }

}