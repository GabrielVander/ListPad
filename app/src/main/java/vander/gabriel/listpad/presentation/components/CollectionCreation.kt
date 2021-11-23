package vander.gabriel.listpad.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        OutlinedTextField(
            value = "",
            label = { Text("Name") },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            label = { Text("Description") },
            onValueChange = {},
        )
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

            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    expanded = true
                }
            ) {
                Text(
                    text = buttonTitle,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

/**
 * A generic dropdown menu
 */
@Composable
fun DropdownMenu(
    colorSelected: Color = MaterialTheme.colors.primary,
    colorBackground: Color = MaterialTheme.colors.onSurface,
    expanded: Boolean,
    selectedIndex: Int,
    items: List<String>,
    onSelect: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    Box {
        content()
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            modifier = Modifier
                .background(
                    color = colorBackground,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            items.forEachIndexed { index, s ->
                if (selectedIndex == index) {
                    DropdownMenuItem(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth()
                            .background(
                                color = colorSelected,
                                shape = RoundedCornerShape(16.dp)
                            ),
                        onClick = { onSelect(index) }
                    ) {
                        Text(
                            text = s,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                } else {
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onSelect(index) }
                    ) {
                        Text(
                            text = s,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}