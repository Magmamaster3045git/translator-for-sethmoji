package com.example.sethmojitranslator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
            label = {
                Text("English")
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = emojiText,
            onValueChange = {
                emojiText = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Emoji")
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                result =
                    if (englishText.isNotBlank()) {
                        "Translation coming soon..."
                    } else if (emojiText.isNotBlank()) {
                        "Reverse translation coming soon..."
                    } else {
                        "Enter some text first."
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

                // Dictionary screen will be added later

            }
        ) {

            Text("Dictionary")

        }

    }

}
