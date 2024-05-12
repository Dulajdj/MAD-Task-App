package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName= "task")
data class Entity (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title: String,
    var priority: String,
    var description: String
)
