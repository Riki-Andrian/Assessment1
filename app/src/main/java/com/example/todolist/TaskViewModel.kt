package com.example.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class TaskViewModel: ViewModel(){
    var taskItems = MutableLiveData<MutableList<TaskItem>>()

    init {
        taskItems.value = mutableListOf()
    }
    fun addTask(taskItem: TaskItem){
       val list = taskItems.value
        list!!.add(taskItem)
        taskItems.postValue(list)
    }

    fun removeItem(taskItem: TaskItem) {
        val list = taskItems.value
        val task = list!!.find { it.id == taskItem.id }!!
        if (task != null){
            list.remove(taskItem)
        }
        taskItems.postValue(list)

    }

    fun markItemAsDone(taskItem: TaskItem) {
        val list = taskItems.value
        val task = list!!.find { it.id == taskItem.id }!!
        if (task.completedDate == null)
            task.completedDate = LocalDate.now()
        taskItems.postValue(list)
    }


}