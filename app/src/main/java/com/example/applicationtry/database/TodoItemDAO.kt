package com.example.applicationtry.database

import androidx.room.*
import com.example.applicationtry.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoItemDAO {
    @Query("SELECT * from todo_item ORDER BY id ASC")
    fun getTodos(): Flow<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todoItem: TodoItem)

    @Query("DELETE from todo_item")
    fun deleteAllTodo()

    @Update
    suspend fun updateDoneTodo(todoItem: TodoItem)
}