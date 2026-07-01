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
    var editingId by remember { mutableStateOf<Int?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text(text = "Dictionary", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = onBack) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // INPUT FIELDS
        OutlinedTextField(
            value = english,
            onValueChange = { english = it },
            label = { Text("English word") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = emoji,
            onValueChange = { emoji = it },
            label = { Text("Emoji") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (english.isNotBlank() && emoji.isNotBlank()) {

                    if (editingId == null) {
                        viewModel.add(
                            DictionaryEntity(
                                english = english,
                                emoji = emoji
                            )
                        )
                    } else {
                        viewModel.update(
                            DictionaryEntity(
                                id = editingId!!,
                                english = english,
                                emoji = emoji
                            )
                        )
                        editingId = null
                    }

                    english = ""
                    emoji = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (editingId == null) "Add" else "Update")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // LIST
        LazyColumn {
            items(entries) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {
                            Text(text = item.english)
                            Text(text = item.emoji, style = MaterialTheme.typography.headlineMedium)
                        }

                        Row {

                            Button(onClick = {
                                english = item.english
                                emoji = item.emoji
                                editingId = item.id
                            }) {
                                Text("Edit")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(onClick = {
                                viewModel.delete(item)
                            }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}
