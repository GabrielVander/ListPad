package vander.gabriel.listpad.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import vander.gabriel.listpad.domain.entities.CollectionCategory

/**
 * The collection creation composable
 */
@Composable
fun CollectionCreation() {
    val items = CollectionCategory.values().map { it.name }

    var expanded by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableStateOf(0) }
    val buttonTitle = items[selectedIndex]

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = "",
            label = { Text("Name") },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Description") },
            onValueChange = {},
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            DropdownMenu(
                colorSelected = MaterialTheme.colors.onSurface,
                colorBackground = MaterialTheme.colors.primary,
                expanded = expanded,
                selectedIndex = selectedIndex,
                items = items,
                onSelect = { index ->
                    selectedIndex = index
                    expanded = false
                },
                onDismissRequest = {
                    expanded = false
                }) {

                OutlinedButton(
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        expanded = true
                    }
                ) {
                    Text(
                        text = buttonTitle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Row {
                Checkbox(
                    checked = true,
                    onCheckedChange = { }
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Urgent")
            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Save")
        }
    }
}
