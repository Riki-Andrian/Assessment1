package com.example.todolist

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.FragmentNewTaskItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

class NewTaskItem(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

 private lateinit var binding: FragmentNewTaskItemBinding
    private lateinit var taskViewModel: TaskViewModel
    private var dueTime: LocalTime? = null

    override fun  onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()


        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.simpanTugas.setOnClickListener {
            val nama = binding.namaTugasInp.text.toString()
            val desk = binding.deskTugasInp.text.toString()
            if (nama.isBlank() || desk.isBlank()){
                val toast = Toast.makeText(requireActivity(), R.string.salah_input, Toast.LENGTH_LONG)
                toast.show()
            }else{
                saveAction()
            }

        }
        binding.setTimeButton.setOnClickListener {
            openTimePicker()
        }

    }

    private fun openTimePicker() {
        if (dueTime==null)
            dueTime = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
            dueTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButton()
        }
        val dialog = TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute, true)
        dialog.setTitle(R.string.due_time)
        dialog.show()
    }

    private fun saveAction() {
        val nama = binding.namaTugasInp.text.toString()
        val desk = binding.deskTugasInp.text.toString()
        if (taskItem == null){
            val tambahTask = TaskItem(nama, desk, dueTime, null)
            taskViewModel.addTask(tambahTask)
        }
        binding.namaTugasInp.setText("")
        binding.deskTugasInp.setText("")
        dismiss()
    }

    private fun updateTimeButton() {
        binding.setTimeButton.text = String.format("%02d:%02d",dueTime!!.hour,dueTime!!.minute)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskItemBinding.inflate(inflater,container,false)
        return binding.root
    }
}