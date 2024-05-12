package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query


@Dao
interface DAO {
    @Insert
    suspend fun insertTask(entity: Entity)

    @Update
    suspend fun updateTask(entity: Entity)

    @Delete
    suspend fun deleteTask(entity: Entity)

    @Query("Delete from task")
    suspend fun deleteAll()

    @Query("SELECT title, priority, description FROM task")
    suspend fun getTask(): List<CardInfo>



}