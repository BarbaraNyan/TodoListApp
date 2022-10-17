package com.example.applicationtry.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.applicationtry.model.TodoItem
import com.example.applicationtry.model.TodoItemTypesConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//import kotlin.reflect.KParameter

@Database(entities = [TodoItem::class], version = 1, exportSchema = false)
@TypeConverters(TodoItemTypesConverter::class)
abstract class TodoRoomDatabase: RoomDatabase() {
    abstract fun todoItemDAO(): TodoItemDAO

    companion object{
        @Volatile
        private var INSTANCE: TodoRoomDatabase? = null

//        fun getDatabase(context: Context, scope: CoroutineScope): TodoRoomDatabase{
        fun getDatabase(context: Context): TodoRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoRoomDatabase::class.java,
                    "todo_database"
                )
//                    .addCallback(TodoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class TodoDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.todoItemDAO())
                }
            }
        }

        suspend fun populateDatabase(todoItemDAO: TodoItemDAO){
            todoItemDAO.deleteAllTodo()

            var todo = TodoItem(title = "First")
            todoItemDAO.addTodo(todo)

            todo = TodoItem(title = "Second")
            todoItemDAO.addTodo(todo)
        }
    }


}