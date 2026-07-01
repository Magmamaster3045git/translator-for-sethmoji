package com.example.sethmojitranslator

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    viewModel: DictionaryViewModel,
    onOpenDictionary: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {

        Text("Sethmoji Translator")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onOpenDictionary) {
            Text("Open Dictionary")
        }
    }
}
