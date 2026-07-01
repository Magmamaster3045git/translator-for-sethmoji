package com.example.sethmojitranslator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString

@Composable
fun HomeScreen(
    viewModel: DictionaryViewModel,
    onOpenDictionary: () -> Unit
) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    val clipboard = LocalClipboardManager.current

    fun translateText(text: String): String {
        // Split but keep punctuation attached
        return text.split(" ").joinToString(" ") { word ->
            val cleanWord = word.replace(Regex("[^\\p{L}\\p{N}]"), "") // remove punctuation for lookup
            val prefix = word.takeWhile { !it.isLetterOrDigit() }
            val suffix = word.takeLastWhile { !it.isLetterOrDigit() }

            val emoji = SethmojiDictionary.englishToEmoji(cleanWord)
            val translated = emoji ?: viewModel.translate(cleanWord)

            prefix + translated + suffix
        }
    }

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
                output = translateText(it)
            },
            label = { Text("Type English or Emoji") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = output,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        clipboard.setText(AnnotatedString(output))
                    }
                ) {
                    Text("Copy")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onOpenDictionary) {
            Text("Open Dictionary Editor")
        }
    }
} 
