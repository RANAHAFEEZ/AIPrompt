package com.example.aipromptsnippetvault.repos

import com.example.aipromptsnippetvault.dao.PromptDao
import com.example.aipromptsnippetvault.entity.PromptEntity
import kotlinx.coroutines.flow.Flow

class PromptRepository(private val promptDao: PromptDao) {
    val allPrompts: Flow<List<PromptEntity>> = promptDao.getAllPrompts()

    suspend fun insert(prompt: PromptEntity) {
        promptDao.insertPrompt(prompt)
    }

    suspend fun delete(prompt: PromptEntity) {
        promptDao.deletePrompt(prompt)
    }
}