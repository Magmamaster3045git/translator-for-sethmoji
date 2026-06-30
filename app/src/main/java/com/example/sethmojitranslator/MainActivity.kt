package com.example.sethmojitranslator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SethmojiTranslatorApp()
        }
    }
}

@Composable
fun SethmojiTranslatorApp() {

    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {

    var englishText by remember { mutableStateOf("") }
    var emojiText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Sethmoji Translator",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = englishText,
            onValueChange = {
                englishText = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("English") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = emojiText,
            onValueChange = {
                emojiText = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Emoji") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                result = when {

                    englishText.isNotBlank() -> {
                        SethmojiDictionary.englishToEmoji(englishText)
                            ?: "No translation found"
                    }

                    emojiText.isNotBlank() -> {
                        SethmojiDictionary.emojiToEnglish(emojiText)
                            ?: "No translation found"
                    }

                    else -> {
                        "Enter something first"
                    }
                }

            }
        ) {
            Text("Translate")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Result",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = result,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                // Dictionary screen later
            }
        ) {
            Text("Dictionary")
        }
    }
}
