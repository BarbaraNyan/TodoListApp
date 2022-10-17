package com.example.applicationtry.database

import androidx.annotation.WorkerThread
import com.example.applicationtry.model.TodoItem
import kotlinx.coroutines.flow.Flow

//1)класс должен возвращать список дел
//2)метод добавления нового дела (дело передаётся как аргумент функции)
//3)в текущей реализации список дел сейчас можно захардкодить (минимум 10-20 значений)
//4)дела должны быть максимально разнообразны, чтобы покрыть все комбинации возможных значений и проверить работу экрана наиболее полным образом

//class TodoItemsRepository {
class TodoItemsRepository(private val todoItemDAO: TodoItemDAO) {
//    private val todoItems: LiveData<TodoItem> = mutableListOf()
    val todoItems: Flow<List<TodoItem>> = todoItemDAO.getTodos()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addTodo(todoItem: TodoItem){
        todoItemDAO.addTodo(todoItem)
    }

    suspend fun updateDoneTodo(todoItem: TodoItem){
        todoItemDAO.updateDoneTodo(todoItem)
    }

//    fun addTodo(todo: TodoItem){
//        todoItems.add(todo)
//    }
//    fun getTodo(position: Int): TodoItem {
//        return todoItems[position]
//    }
//    fun getSize(): Int{
//        return todoItems.size
//    }
//
//    fun removeAll(){
//        todoItems.removeAll { todoItem ->
//            todoItem.isDone
//        }
//    }
//
////    fun deleteTodo(id: Int){
////        todoItems.removeAt(id)
////    }
//
//    init{
//        addTodo(TodoItem(0, "First thing to do..", Priority.HIGH, dateCreated = Date(), dateChanged = Date(), deadline = Date()))
//        addTodo(TodoItem(1, "Second thing to do..", Priority.LOW, dateCreated = Date(), dateChanged = Date(), deadline = Date()))
//        addTodo(TodoItem(2, "Third thing to do..", Priority.MEDIUM, dateCreated = Date(), dateChanged = Date(), deadline = Date()))
//    }
//
//    fun getTodos(): LiveData<List<TodoItem>> {
//        return todoItems
//    }
}