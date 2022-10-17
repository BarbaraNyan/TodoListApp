package com.example.applicationtry.fragments

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicationtry.*
import com.example.applicationtry.databinding.FragmentTodoListBinding
import com.example.applicationtry.model.TodoItem
import com.example.applicationtry.viewmodel.TodoViewModel
//import com.example.applicationtry.viewmodel.TodoViewModelFactory

class TodoListFragment: Fragment(), TodoAdapter.OnItemClickListener{
//    val database by lazy { TodoRoomDatabase.getDatabase(requireContext()) }
//    val repository by lazy { TodoItemsRepository(database.todoItemDAO()) }

    private lateinit var binding: FragmentTodoListBinding
    private var doneKol = 0

//    val applicationScope = CoroutineScope(SupervisorJob())
//    val database by lazy { TodoRoomDatabase.getDatabase(requireContext(), applicationScope) }
//    val repository by lazy { TodoItemsRepository(database.todoItemDAO()) }
//    private val todoViewModel: TodoViewModel by viewModels{
//        TodoViewModelFactory(ServiceLocator.getRepository().repository)
//    }
//    private val serviceLocator = ServiceLocator.getRepository()
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoListBinding.inflate(layoutInflater)


//        todoAdapter = TodoAdapterNew(requireContext(), this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todoAdapter = TodoAdapter(this)

        todoViewModel.allTodo.observe(viewLifecycleOwner as LifecycleOwner){todos ->
            todos.let { todoAdapter.submitList(it) }
        }

        binding.apply {
            rvTodoItems.apply {
                adapter = todoAdapter
                layoutManager = LinearLayoutManager(requireContext())
//                getChildViewHolder(R.id.cbDone)
            }
            fabAdd.apply {
                setOnClickListener{
                    findNavController().navigate(R.id.action_todoListFragment_to_addTodoFragment)
                }
            }
        }
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TodoListFragment()
    }

    override fun onCheckBoxListener(todoItem: TodoItem, isChecked: Boolean) {
        if(isChecked){
            ++doneKol
        }
        else {
            --doneKol
        }
        binding.textDoneKol.text = "Выполнено - $doneKol"
    }

    override fun onItemDelete(todoItem: TodoItem) {
        TODO("Not yet implemented")
    }

}