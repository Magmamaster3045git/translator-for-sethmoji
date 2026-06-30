@Composable
fun DictionaryScreen(
    viewModel: DictionaryViewModel,
    onBack: () -> Unit
) {

    val entries by viewModel.entries.collectAsState()

    var english by remember { mutableStateOf("") }
    var emoji by remember { mutableStateOf("") }
    var editingId by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Dictionary Editor", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = english,
            onValueChange = { english = it },
            label = { Text("English") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = emoji,
            onValueChange = { emoji = it },
            label = { Text("Emoji") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                if (editingId == null) {
                    viewModel.add(
                        DictionaryEntity(english = english, emoji = emoji)
                    )
                } else {
                    viewModel.update(
                        DictionaryEntity(
                            id = editingId!!,
                            english = english,
                            emoji = emoji
                        )
                    )
                }

                english = ""
                emoji = ""
                editingId = null
            }
        ) {
            Text(if (editingId == null) "Add" else "Update")
        }

        Spacer(modifier = Modifier.height(10.dp))

        androidx.compose.foundation.lazy.LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(entries) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {
                            Text(item.english)
                            Text(item.emoji)
                        }

                        Row {

                            Button(onClick = {
                                english = item.english
                                emoji = item.emoji
                                editingId = item.id
                            }) {
                                Text("Edit")
                            }

                            Spacer(modifier = Modifier.width(6.dp))

                            Button(onClick = {
                                viewModel.delete(item)
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
