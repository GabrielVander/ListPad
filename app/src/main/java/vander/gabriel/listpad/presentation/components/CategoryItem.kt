package vander.gabriel.listpad.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import vander.gabriel.listpad.domain.entities.CollectionCategory
import vander.gabriel.listpad.presentation.theme.CATEGORY_INDICATOR_SIZE
import vander.gabriel.listpad.presentation.theme.LARGE_PADDING
import vander.gabriel.listpad.presentation.theme.Typography

@Composable
fun CategoryItem(category: CollectionCategory) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(CATEGORY_INDICATOR_SIZE)) {
            drawCircle(color = category.color)
        }
        Text(
            modifier = Modifier
                .padding(start = LARGE_PADDING),
            text = category.name,
            style = Typography.subtitle1,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
@Preview
fun GeneralCategoryItemPreview() {
    CategoryItem(category = CollectionCategory.GENERAL)
}

@Composable
@Preview
fun AppointmentsCategoryItemPreview() {
    CategoryItem(category = CollectionCategory.APPOINTMENTS)
}

@Composable
@Preview
fun ShoppingCategoryItemPreview() {
    CategoryItem(category = CollectionCategory.SHOPPING)
}

@Composable
@Preview
fun TasksCategoryItemPreview() {
    CategoryItem(category = CollectionCategory.TASKS)
}
