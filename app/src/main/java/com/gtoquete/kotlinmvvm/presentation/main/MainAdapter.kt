package com.gtoquete.kotlinmvvm.presentation.main

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gtoquete.kotlinmvvm.R
import com.gtoquete.kotlinmvvm.data.model.Note
import com.gtoquete.kotlinmvvm.databinding.ItemNotesListBinding
import com.gtoquete.kotlinmvvm.infrastructure.AdapterItemsContract

class MainAdapter(private var notes: List<Note>,
                  private val onCardClickListener: OnCardClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AdapterItemsContract {

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

    override fun replaceItems(items: List<*>) {
        this.notes = items as List<Note>
        notifyDataSetChanged()
    }

    inner class BindingHolder(private val binding: ItemNotesListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note, viewHolder: RecyclerView.ViewHolder) {
            binding.cardviewNote.setOnClickListener { onCardClickListener.onCardClick(notes[viewHolder.adapterPosition]) }
            binding.note = note
            binding.executePendingBindings()
        }
    }

    interface OnCardClickListener {
        fun onCardClick(note: Note)
    }

}