package com.example.aipromptsnippetvault

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

// Data model for UI preview
data class PromptItem(val id: Int, val title: String, val category: String, val snippet: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromptVaultScreen() {
    // Dummy list for demonstration
    val samplePrompts = listOf(
        PromptItem(1, "Clean Architecture ViewModel Setup", "Coding", "Write a clean architecture ViewModel in Kotlin using coroutines and stateflow..."),
        PromptItem(2, "Blog Post SEO Outline Generator", "Writing", "Act as an expert SEO copywriter and create a detailed outline for the keyword..."),
        PromptItem(3, "Jetpack Compose LazyColumn Optimization", "Coding", "Provide tips and code snippet to optimize heavy LazyColumns in Jetpack Compose..."),
        PromptItem(4, "Polite Client Email Follow-up", "Productivity", "Draft a professional yet polite follow-up email for a delayed project milestone...")
    )

    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("AI Prompt Vault", fontWeight = Bold, fontSize = 20.sp)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E1E2E),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle Add New Prompt */ },
                containerColor = Color(0xFF6C63FF),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Prompt")
            }
        },
        containerColor = Color(0xFF121218)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search your prompts...", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1E1E2E), RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6C63FF),
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Saved Vault",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // List of Prompts
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(samplePrompts) { prompt ->
                    PromptCard(prompt = prompt)
                }
            }
        }
    }
}

@Composable
fun PromptCard(prompt: PromptItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E2E)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click to view full prompt */ }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = prompt.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = Bold,
                    modifier = Modifier.weight(1f)
                )
                
                // Category Chip
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF2A2A3D)
                ) {
                    Text(
                        text = prompt.category,
                        color = Color(0xFF6C63FF),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = prompt.snippet,
                color = Color.Gray,
                fontSize = 13.sp,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { /* Handle Copy to Clipboard */ },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy Prompt",
                        tint = Color(0xFF6C63FF),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PromptVaultScreenPreview() {
    PromptVaultScreen()
}

@Preview(showBackground = true, backgroundColor = 0xFF121218)
@Composable
fun PromptCardPreview() {
    val samplePrompt = PromptItem(
        id = 1,
        title = "Clean Architecture ViewModel Setup",
        category = "Coding",
        snippet = "Write a clean architecture ViewModel in Kotlin using coroutines and stateflow..."
    )
    PromptCard(prompt = samplePrompt)
}
