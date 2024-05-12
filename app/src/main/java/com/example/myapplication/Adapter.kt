package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ViewBinding

class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val priority = binding.priority
        val description = binding.description
        val layout = binding.mylayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]

        // Set background color based on priority
        holder.layout.setBackgroundColor(
            when (currentItem.priority.toLowerCase()) {
                "high" -> Color.parseColor("#F05454")
                "medium" -> Color.parseColor("#EDC988")
                "low" -> Color.parseColor("#00917C")
                else -> Color.parseColor("#B3E5FC")

            }
        )

        holder.title.text = data[position].title
        holder.priority.text= data[position].priority
        holder.description.text=data[position].description

        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,UpdateCard::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}