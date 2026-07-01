package com.example.sethmojitranslator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    viewModel: DictionaryViewModel,
    onOpenDictionary: () -> Unit
) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Sethmoji Translator",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = input,
            onValueChange = {
                input = it

                // Try English → Emoji first
                val emoji = SethmojiDictionary.englishToEmoji(it)

                // If not found, try Emoji → English via ViewModel
                val english = viewModel.translateEmoji(it)

                output = when {
                    emoji != null -> emoji
                    english != it -> english
                    else -> it
                }
            },
            label = { Text("Type English or Emoji") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = output,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onOpenDictionary) {
            Text("Open Dictionary Editor")
        }
    }
}
