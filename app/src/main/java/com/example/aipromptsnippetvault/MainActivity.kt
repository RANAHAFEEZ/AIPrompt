package com.example.aipromptsnippetvault

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aipromptsnippetvault.entity.PromptEntity
import com.example.aipromptsnippetvault.viewmodel.PromptViewModel

class MainActivity : AppCompatActivity() {
    private val promptViewModel: PromptViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainVaultScreen(viewModel = promptViewModel)
            }
//            PromptVaultScreen()
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainVaultScreen(viewModel: PromptViewModel) {
        // Collect data from Room database as State
        val prompts by viewModel.allPrompts.collectAsState(initial = emptyList())

        // State to control Add Prompt Dialog
        var showAddDialog by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("AI Prompt Vault") }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { showAddDialog = true }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Prompt")
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                if (prompts.isEmpty()) {
                    // Empty state
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                        Text("No prompts saved yet. Tap '+' to add one!", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                } else {
                    // Real data LazyColumn
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = prompts,
                            key = { it.id } // Unique key denay se performance bhi behtar hoti hai
                        ){ prompt ->
                            PromptCardItem(prompt = prompt, onDelete = {
                                viewModel.deletePrompt(prompt)
                            })
                        }
                    }
                }
            }

            // Add Prompt Dialog popup
            if (showAddDialog) {
                AddPromptDialog(
                    onDismiss = { showAddDialog = false },
                    onSave = { title, text, category ->
                        viewModel.addPrompt(title, text, category)
                        showAddDialog = false
                    }
                )
            }
        }
    }

    @Composable
    fun PromptCardItem(prompt: PromptEntity, onDelete: () -> Unit) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = prompt.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = prompt.category, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = prompt.promptText, style = MaterialTheme.typography.bodyMedium)

                // Delete button option ya long-press handler yahan laga sakte ho
            }
        }
    }

    @Composable
    fun AddPromptDialog(onDismiss: () -> Unit, onSave: (String, String, String) -> Unit) {
        var title by remember { mutableStateOf("") }
        var promptText by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("Coding") }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Add New Prompt") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") }
                    )
                    OutlinedTextField(
                        value = promptText,
                        onValueChange = { promptText = it },
                        label = { Text("Prompt Content") }
                    )
                    OutlinedTextField(
                        value = category,
                        onValueChange = { category = it },
                        label = { Text("Category (e.g. Coding, Writing)") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (title.isNotBlank() && promptText.isNotBlank()) {
                            onSave(title, promptText, category)
                        }
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }
}