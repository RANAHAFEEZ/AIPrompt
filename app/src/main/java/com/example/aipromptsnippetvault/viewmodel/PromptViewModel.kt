package com.example.aipromptsnippetvault.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.aipromptsnippetvault.database.PromptDatabase
import com.example.aipromptsnippetvault.entity.PromptEntity
import com.example.aipromptsnippetvault.repos.PromptRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PromptViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PromptRepository

    val allPrompts: StateFlow<List<PromptEntity>>

    init {
        val promptDao = PromptDatabase.getDatabase(application).promptDao()
        repository = PromptRepository(promptDao)
        allPrompts = repository.allPrompts
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    fun addPrompt(title: String, promptText: String, category: String) {
        viewModelScope.launch {
            repository.insert(PromptEntity(title = title, promptText = promptText, category = category))
        }
    }

    fun deletePrompt(prompt: PromptEntity) {
        viewModelScope.launch {
            repository.delete(prompt)
        }
    }
}