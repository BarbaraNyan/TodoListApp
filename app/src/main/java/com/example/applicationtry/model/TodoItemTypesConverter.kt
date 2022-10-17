package com.example.applicationtry.model

import androidx.room.TypeConverter
import java.util.*

class TodoItemTypesConverter {
    @TypeConverter
    fun fromDate(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch : Long?) : Date ? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }
}