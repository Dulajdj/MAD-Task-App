package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.myapplication.databinding.ActivityCreateCartBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var binding: ActivityCreateCartBinding

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "task"
        ).build()

        binding.saveButton.setOnClickListener {
            val title = binding.createTitle.text.toString()
            val priority = binding.createPriority.text.toString()
            val description=binding.createDescription.text.toString()

            if (title.isNotBlank() && priority.isNotBlank() && description.isNotBlank()){
                DataObject.setData(title,priority,description)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0,title,priority,description))
                }
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Please enter title,priority and description",Toast.LENGTH_SHORT).show()


            }
        }
    }
}