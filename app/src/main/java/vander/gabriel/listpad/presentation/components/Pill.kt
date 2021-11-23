package vander.gabriel.listpad.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * A basic pill shape component
 */
@Composable
fun Pill(color: Color, label: String) {
    Box(
        modifier = Modifier
            .background(
                color = color,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(5.dp)

    ) {
        Text(text = label)
    }
}