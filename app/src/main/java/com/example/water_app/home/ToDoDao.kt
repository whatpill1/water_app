package com.example.water_app.home

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDoEntity")
    fun getAll() : List<ToDoEntity>

    @Insert
    fun insertTodo(todo: ToDoEntity)

    @Delete
    fun deleteTodo(todo: ToDoEntity)
}