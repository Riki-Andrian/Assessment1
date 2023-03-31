package com.example.todolist

import android.content.Context
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TaskItemListBinding
import java.time.format.DateTimeFormatter
import java.util.*

class TaskViewHolder(
    private val contex: Context,
    private val binding: TaskItemListBinding,
   private val taskViewModel: TaskViewModel
): RecyclerView.ViewHolder(binding.root) {

    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")


    fun bindTaskItem(taskItem: TaskItem){
        binding.namaTugas.text=taskItem.nama

        if(taskItem.isCompleted()){
            binding.namaTugas.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        }

        binding.completeButton.setImageResource(taskItem.imageResource())
        binding.completeButton.setColorFilter(taskItem.imageColor(contex))

        binding.completeButton.setOnClickListener{
            taskViewModel.markItemAsDone(taskItem)
        }


        binding.deleteButton.setOnClickListener{
            taskViewModel.removeItem(taskItem)

        }

        if (taskItem.dueTime != null)
            binding.dueTime.text = timeFormat.format(taskItem.dueTime)
        else
            binding.dueTime.text = ""

    }
}

