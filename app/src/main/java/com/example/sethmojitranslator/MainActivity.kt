package com.example.sethmojitranslator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SethmojiApp()
        }
    }
}

@Composable
fun SethmojiApp() {

    val viewModel: DictionaryViewModel = viewModel()

    var currentScreen by remember { mutableStateOf("home") }

    MaterialTheme {
        Surface {

            when (currentScreen) {

                "home" -> HomeScreen(
                    viewModel = viewModel,
                    onOpenDictionary = {
                        currentScreen = "dictionary"
                    }
                )

                "dictionary" -> DictionaryScreen(
                    viewModel = viewModel,
                    onBack = {
                        currentScreen = "home"
                    }
                )
            }
        }
    }
}
