package com.example.sethmojitranslator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DictionaryScreen(
    onBack: () -> Unit
) {

    var entries by remember {
        mutableStateOf(SethmojiDictionary.entries.toMutableList())
    }

    var search by remember { mutableStateOf("") }

    val filtered = entries.filter {
        it.english.contains(search, ignoreCase = true) ||
        it.emoji.contains(search)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Dictionary",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            itemsIndexed(filtered) { index, item ->

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

                            Text(text = item.english)
                            Text(text = item.emoji)

                        }

                        Row {

                            Button(onClick = {
                                // EDIT (simple version: swap values manually later)
                            }) {
                                Text("Edit")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(onClick = {
                                entries = entries.toMutableList().apply {
                                    removeAt(index)
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
