package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TaskItemListBinding

class TaskAdapter(
    private val taskItem: List<TaskItem>,
    private val taskViewModel: TaskViewModel
): RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskItemListBinding.inflate(inflater, parent, false)
        return TaskViewHolder(parent.context, binding, taskViewModel)
    }

    override fun getItemCount(): Int {
        return taskItem.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindTaskItem(taskItem[position])
    }


}