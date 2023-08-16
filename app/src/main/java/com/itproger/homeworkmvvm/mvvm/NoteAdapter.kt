package com.itproger.homeworkmvvm.mvvm

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.itproger.homeworkmvvm.databinding.ItemNoteBinding

class NoteAdapter (private val viewModel: NoteViewModel) :
    Adapter<NoteAdapter.NoteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
    val binding =ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
    val note = viewModel.noteList[position]
        holder.onBind(note)
    }

    override fun getItemCount(): Int {
        return viewModel.noteList.size
    }

    inner class NoteViewHolder (private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: NoteModel) {
            binding.noteTitle.text = note.title
            binding.noteCheckbox.isChecked = note.isCompleted
            binding.noteCheckbox.setOnCheckedChangeListener { _, isChecked ->
                note.isCompleted = isChecked
            }
            binding.root.setOnLongClickListener {
                showDeleteNoteDialog(note)
                true
            }
        }

        private fun showDeleteNoteDialog(note: NoteModel) {
            val builder = AlertDialog.Builder(binding.root.context)
            builder.setMessage("Удалить задачу?").setPositiveButton("да") {_, _ ->
                viewModel.removeNote(note)
                notifyDataSetChanged()
            }.setNegativeButton("нет", null).show()
        }
    }
}