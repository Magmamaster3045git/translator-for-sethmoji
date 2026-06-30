package com.example.sethmojitranslator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun DictionaryScreen(
    onBack: () -> Unit
) {

    val db = AppDatabase.getDatabase(androidx.compose.ui.platform.LocalContext.current)
    val dao = db.dictionaryDao()

    val scope = rememberCoroutineScope()

    var entries by remember { mutableStateOf(listOf<DictionaryEntity>()) }

    var english by remember { mutableStateOf("") }
    var emoji by remember { mutableStateOf("") }

    var editingId by remember { mutableStateOf<Int?>(null) }

    // Load data
    LaunchedEffect(true) {
        withContext(Dispatchers.IO) {
            entries = dao.getAll()
        }
    }

    fun refresh() {
        scope.launch(Dispatchers.IO) {
            entries = dao.getAll()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Dictionary Editor",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = english,
            onValueChange = { english = it },
            label = { Text("English") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = emoji,
            onValueChange = { emoji = it },
            label = { Text("Emoji") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                scope.launch(Dispatchers.IO) {

                    if (editingId == null) {
                        dao.insert(
                            DictionaryEntity(
                                english = english,
                                emoji = emoji
                            )
                        )
                    } else {
                        dao.update(
                            DictionaryEntity(
                                id = editingId!!,
                                english = english,
                                emoji = emoji
                            )
                        )
                    }

                    english = ""
                    emoji = ""
                    editingId = null

                    entries = dao.getAll()
                }
            }
        ) {
            Text(if (editingId == null) "Add" else "Update")
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(entries) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {
                            Text(item.english)
                            Text(item.emoji)
                        }

                        Row {

                            Button(onClick = {
                                english = item.english
                                emoji = item.emoji
                                editingId = item.id
                            }) {
                                Text("Edit")
                            }

                            Spacer(modifier = Modifier.width(6.dp))

                            Button(onClick = {

                                scope.launch(Dispatchers.IO) {
                                    dao.delete(item)
                                    entries = dao.getAll()
                                }

                            }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBack
        ) {
            Text("Back")
        }
    }
}
