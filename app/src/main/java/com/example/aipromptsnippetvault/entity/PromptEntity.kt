package com.example.aipromptsnippetvault.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prompts_table")
data class PromptEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val promptText: String,
    val category: String, // e.g., "Coding", "Writing", "Productivity"
    val createdAt: Long = System.currentTimeMillis()
)