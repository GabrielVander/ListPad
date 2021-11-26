package vander.gabriel.listpad.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * A generic dropdown menu
 */
@Composable
fun DropdownMenu(
    colorSelected: Color = MaterialTheme.colors.primary,
    colorBackground: Color = MaterialTheme.colors.onSurface,
    items: List<String>,
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val buttonTitle = items[selectedIndex]

    var expanded by remember { mutableStateOf(false) }

    Box {
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
        androidx.compose.material.DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(
                    color = colorBackground,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            items.forEachIndexed { index, text ->
                if (selectedIndex == index) {
                    DropdownMenuItem(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth()
                            .background(
                                color = colorSelected,
                                shape = RoundedCornerShape(16.dp)
                            ),
                        onClick = {
                            selectedIndex = index
                            expanded = false
                        }
                    ) {
                        Text(
                            text = text,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                } else {
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            selectedIndex = index
                            expanded = false
                        }
                    ) {
                        Text(
                            text = text,
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