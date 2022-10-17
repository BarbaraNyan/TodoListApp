package com.example.applicationtry.viewmodel

import android.app.Application
import androidx.lifecycle.*
//import androidx.lifecycle.*
import com.example.applicationtry.database.TodoItemsRepository
import com.example.applicationtry.database.TodoRoomDatabase
import com.example.applicationtry.model.TodoItem
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application){
//    private val applicationScope = CoroutineScope(SupervisorJob())
//    private val database = TodoRoomDatabase.getDatabase(application)
//    private val repository: TodoItemsRepository = TodoItemsRepository(database.todoItemDAO())

    private val database by lazy { TodoRoomDatabase.getDatabase(application) }
    private val repository by lazy { TodoItemsRepository(database.todoItemDAO()) }
    val allTodo: LiveData<List<TodoItem>> = repository.todoItems.asLiveData()

    fun addTodo(todoItem: TodoItem) = viewModelScope.launch {
        repository.addTodo(todoItem)
    }

    fun updateDoneTodo(todoItem: TodoItem) = viewModelScope.launch {
        repository.updateDoneTodo(todoItem)
    }

}

//class TodoViewModelFactory(private val repository: TodoItemsRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return TodoViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}