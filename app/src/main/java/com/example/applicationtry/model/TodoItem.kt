package com.example.applicationtry.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.applicationtry.Priority
import java.util.*

//1)класс должен быть kotlin data class
//2)id дела, обязательный параметр – String
//3)текст дела, обязательный параметр – String
//4)важность/значимость дела, обязательный параметр, должно быть 3 значения: “низкая”, “обычная”, “срочная»
//5)дедлайн выполнения, опциональный параметр
//6)флаг выполнения (сделана или нет), обязательный параметр
//7)дата создания, обязательный параметр
//8)дата изменения, опциональный параметр

@Entity(tableName = "todo_item")
data class TodoItem(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    var title: String = "",
    var priority: Priority = Priority.LOW,
    var deadline: Date = Date(),
    var isDone: Boolean = false,
    var dateCreated: Date = Date(),
    var dateChanged: Date = Date()
//    var isChecked: Boolean = false
)
