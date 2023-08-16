package com.itproger.homeworkmvvm.activity

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.itproger.homeworkmvvm.mvvm.NoteAdapter
import com.itproger.homeworkmvvm.mvvm.NoteModel
import com.itproger.homeworkmvvm.mvvm.NoteViewModel
import com.itproger.homeworkmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NoteViewModel by viewModels()
    private  lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
        adapter = NoteAdapter(viewModel)
        binding.showRv.layoutManager = LinearLayoutManager(this)
        binding.showRv.adapter = adapter
        binding.addBtn.setOnClickListener {
            showAddNoteDialog()
        }
    }

    private fun showAddNoteDialog() {
        val builder = AlertDialog.Builder(this)
        val input = EditText(this)
        builder.setTitle("Добавить задачу")
            .setView(input)
            .setPositiveButton("Добавить") { _, _ ->
                val noteTitle = input.text.toString()
                if (noteTitle.isNotEmpty()) {
                    viewModel.addNote(NoteModel(noteTitle))
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }
}