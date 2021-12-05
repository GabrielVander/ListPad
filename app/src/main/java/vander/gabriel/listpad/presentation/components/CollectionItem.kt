package vander.gabriel.listpad.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.entities.CollectionCategory
import vander.gabriel.listpad.presentation.theme.CATEGORY_INDICATOR_SIZE
import vander.gabriel.listpad.presentation.theme.COLLECTION_ELEVATION
import vander.gabriel.listpad.presentation.theme.LARGE_PADDING

@ExperimentalMaterialApi
@Composable
fun CollectionItem(
    collection: Collection,
    onClick: (collection: Collection) -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = COLLECTION_ELEVATION,
        onClick = { onClick(collection) }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth(),
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = collection.name,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                if (collection.isUrgent) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(CATEGORY_INDICATOR_SIZE)
                        ) {
                            drawCircle(
                                color = Color.Red
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = collection.description,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))
            Pill(
                color = collection.category.color.copy(alpha = .5f),
                label = collection.category.name,
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun CollectionItemPreview() {
    CollectionItem(
        collection = Collection(
            id = "",
            name = "Flush",
            description = "She insisted that cleaning out your closet was the key to good driving",
            isUrgent = false,
            category = CollectionCategory.SHOPPING
        )
    )
}