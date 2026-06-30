package com.example.sethmojitranslator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SethmojiApp()
        }
    }
}

/* ---------------- VIEWMODEL FACTORY (IMPORTANT FOR ROOM) ---------------- */

class DictionaryViewModelFactory(
    private val dao: DictionaryDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DictionaryViewModel(dao) as T
    }
}

/* ---------------- ROOT APP ---------------- */

@Composable
fun SethmojiApp() {

    val context = LocalContext.current
    val dao = AppDatabase.getDatabase(context).dictionaryDao()

    val viewModel: DictionaryViewModel = viewModel(
        factory = DictionaryViewModelFactory(dao)
    )

    var currentScreen by remember { mutableStateOf("home") }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

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
