package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreen : AppCompatActivity() {
    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        // Use coroutine to fetch tasks from the database in the background
        GlobalScope.launch(Dispatchers.IO) {
            val tasks = database.dao().getTask()

            // Once tasks are retrieved, update DataObject and start MainActivity
            withContext(Dispatchers.Main) {
                DataObject.listdata = tasks as MutableList<CardInfo>
                startMainActivity()
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Finish the SplashScreen activity
    }
}