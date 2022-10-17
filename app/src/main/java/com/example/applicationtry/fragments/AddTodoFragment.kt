package com.example.applicationtry.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.applicationtry.*
import com.example.applicationtry.databinding.FragmentAddTodoBinding
import com.example.applicationtry.model.TodoItem
import com.example.applicationtry.viewmodel.TodoViewModel
//import com.example.applicationtry.viewmodel.TodoViewModelFactory
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [AddTodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTodoFragment : Fragment(){
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentAddTodoBinding
//    private lateinit var todoItemsRepository : TodoItemsRepository
    private val todoViewModel: TodoViewModel by viewModels()

    private lateinit var todoItem: TodoItem
//    private lateinit var todoPriority: Priority
//    private lateinit var todoDeadline: Date
//    private val todoViewModel: TodoViewModel by viewModels{
//        TodoViewModelFactory((activity?.application as TodoApplication).repository)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_todo, container, false)
//        todoViewModel
        todoItem = TodoItem()
        binding = FragmentAddTodoBinding.inflate(layoutInflater)

//        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
//        todoViewModel.

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPriority()
        setDatePicker()

        binding.apply {
            imgBtnCloseTodo.setOnClickListener {
                findNavController().navigate(R.id.action_addTodoFragment_to_todoListFragment)
            }
            btnSaveTodo.setOnClickListener {
                if (insertNewTodo())
                    findNavController().navigate(R.id.action_addTodoFragment_to_todoListFragment)
            }
            btnClearTodo.setOnClickListener {
                deleteAllFillFields()
            }
        }
    }

    private fun insertNewTodo(): Boolean{
        return if(inputCheck(binding.etTextTodo.text.toString(), todoItem.deadline)){
            todoItem = TodoItem(title = todoItem.title, priority = todoItem.priority, deadline = todoItem.deadline )
            todoViewModel.addTodo(todoItem)
            true
        } else{
            Toast.makeText(requireContext(),"Заполните все поля", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun inputCheck(todoTitle: String, todoDeadline: Date): Boolean{
        return if(!TextUtils.isEmpty(todoTitle)){
            todoItem.title = todoTitle
            true
        } else false
    }

    private fun setPriority(){
        ArrayAdapter.createFromResource(requireContext(), R.array.priority_array, android.R.layout.simple_spinner_item)
            .also {
                    adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spPriority.adapter = adapter
            }
        binding.spPriority.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0?.getItemAtPosition(p2).toString()
//                todoItem.priority = when (selectedItem){
//                    Priority.LOW.toString() -> Priority.LOW
//                    Priority.MEDIUM.toString() -> Priority.MEDIUM
//                    else -> {Priority.HIGH}
//                }

                todoItem.priority = if (selectedItem == Priority.LOW.toString())
                    Priority.LOW
                else if(selectedItem == Priority.MEDIUM.toString())
                    Priority.MEDIUM
                else
                    Priority.HIGH
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

                TODO("Not yet implemented")
            }

        }
        binding.spPriority.setSelection(0)
    }

    private fun setDatePicker(){
        val c: Calendar = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        binding.switchTodoDatePicker.setOnCheckedChangeListener { _, isChecked ->

            if(isChecked) {
                val datePickerDialog = DatePickerDialog(requireContext(),
                    { datePicker, year, month, day ->
                        val pickedDate = Calendar.getInstance()
                        pickedDate.set(year, month, day)
                        todoItem.deadline = Date(pickedDate.timeInMillis)
                        binding.tvDateDeadline.text = "" + day + "/" + month + "/" + year
//                        todoItem.deadline = Date(c.timeInMillis(datePicker))
                    }, mYear, mMonth, mDay
                )
                datePickerDialog.show()
                datePickerDialog.setOnCancelListener {
                    binding.switchTodoDatePicker.isChecked = false
                }
            }
            else{
//                binding.switchTodoDatePicker.isChecked = false
                binding.tvDateDeadline.text = "Нет"
//                todoItem.deadline = Date()
            }
        }
    }

    private fun deleteAllFillFields(){
        binding.apply {
            etTextTodo.text.clear()
            spPriority.setSelection(0)
            tvDateDeadline.text = "Нет"
        }

        //very bad code//
        todoItem.title = ""
        todoItem.priority = Priority.LOW
        todoItem.deadline = Date()
    }


    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            AddTodoFragment()
    }


}

