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
            onValueChange = { text ->
                input = text

                val tokens = text.split(" ")

                val translated = tokens.map { token ->

                    val match = Regex("([A-Za-zÀ-ÿ]+|[\\p{Emoji_Presentation}\\p{Extended_Pictographic}]+)([^A-Za-zÀ-ÿ\\p{Emoji_Presentation}\\p{Extended_Pictographic}]*)")
                        .find(token)

                    if (match != null) {
                        val word = match.groupValues[1]
                        val punctuation = match.groupValues[2]

                        val emojiToEnglish = viewModel.translateEmoji(word)

                        val translatedWord =
                            if (emojiToEnglish != word) {
                                emojiToEnglish
                            } else {
                                SethmojiDictionary.englishToEmoji(word) ?: word
                            }

                        translatedWord + punctuation
                    } else {
                        token
                    }
                }

                output = translated.joinToString(" ")
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
