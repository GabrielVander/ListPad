package vander.gabriel.listpad.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun <T> Dismissible(
    item: T,
    directions: Set<DismissDirection> = setOf(
        DismissDirection.StartToEnd,
        DismissDirection.EndToStart,
    ),
    defaultBackgroundColor: Color = Color.White,
    dismissedToStartBackgroundColor: Color = Color.Red,
    dismissedToEndBackgroundColor: Color = Color.Green,
    startToEndIcon: ImageVector = Icons.Default.Done,
    endToStartIcon: ImageVector = Icons.Default.Delete,
    dismissed: (item: T) -> Unit,
    content: @Composable () -> Unit,
) {
    val dismissState = rememberDismissState()
    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
        dismissed(item)
    }

    SwipeToDismiss(
        state = dismissState,
        modifier = Modifier.padding(vertical = 1.dp),
        directions = directions,
        dismissThresholds = { direction ->
            FractionalThreshold(
                if (direction == DismissDirection.StartToEnd) 0.25f
                else 0.5f
            )
        },
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss

            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> defaultBackgroundColor
                    DismissValue.DismissedToStart -> dismissedToStartBackgroundColor
                    DismissValue.DismissedToEnd -> dismissedToEndBackgroundColor
                }
            )
            val alignment = when (direction) {
                DismissDirection.StartToEnd -> Alignment.CenterStart
                DismissDirection.EndToStart -> Alignment.CenterEnd
            }
            val icon = when (direction) {
                DismissDirection.StartToEnd -> startToEndIcon
                DismissDirection.EndToStart -> endToStartIcon
            }
            val scale by animateFloatAsState(
                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 20.dp)
                    .clickable {
                        dismissed(item)
                    },
                contentAlignment = alignment
            ) {
                Button(onClick = { dismissed(item) }) {
                    Image(
                        imageVector = icon,
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .scale(scale)
                    )
                }
            }
        },
        dismissContent = {
            content()
        }
    )
}