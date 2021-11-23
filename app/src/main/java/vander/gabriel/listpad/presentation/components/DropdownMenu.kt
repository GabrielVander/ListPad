package vander.gabriel.listpad.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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
        androidx.compose.material.DropdownMenu(
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