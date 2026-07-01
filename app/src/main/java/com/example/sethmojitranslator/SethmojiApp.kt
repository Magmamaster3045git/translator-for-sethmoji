package com.example.sethmojitranslator

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SethmojiApp() {

    val viewModel: DictionaryViewModel = viewModel()

    var screen by remember { mutableStateOf("home") }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            when (screen) {

                "home" -> HomeScreen(
                    viewModel = viewModel,
                    onOpenDictionary = { screen = "dictionary" }
                )

                "dictionary" -> DictionaryScreen(
                    viewModel = viewModel,
                    onBack = { screen = "home" }
                )
            }
        }
    }
}
