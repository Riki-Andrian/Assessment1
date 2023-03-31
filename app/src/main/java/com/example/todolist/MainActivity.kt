package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.tambahTask.setOnClickListener {
            NewTaskItem(null).show(supportFragmentManager, "Tambah Tugas")
        }

        setRecycleView()
    }

    private fun setRecycleView() {
        taskViewModel.taskItems.observe(this){
            binding.todoListRecycler.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskAdapter(it, taskViewModel)
            }
        }
    }

}

