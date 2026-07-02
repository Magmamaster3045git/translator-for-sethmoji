package com.example.sethmojitranslator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DictionaryScreen(
    viewModel: DictionaryViewModel,
    onBack: () -> Unit
) {

    val entries by viewModel.entries.collectAsState()

    var english by remember { mutableStateOf("") }
    var emoji by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(onClick = onBack) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = english,
            onValueChange = { english = it },
            label = { Text("English word") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = emoji,
            onValueChange = { emoji = it },
            label = { Text("Emoji") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            viewModel.addEntry(english, emoji)
            english = ""
            emoji = ""
        }) {
            Text("Add")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(entries) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${item.english} → ${item.emoji}")

                        Button(onClick = {
                            viewModel.deleteEntry(item.id)
                        }) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}
