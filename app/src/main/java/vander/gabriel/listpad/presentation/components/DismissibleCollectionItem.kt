package vander.gabriel.listpad.presentation.components

import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import vander.gabriel.listpad.domain.entities.Collection

@ExperimentalMaterialApi
@Composable
fun DismissibleCollectionItem(
    collection: Collection,
    onDeleteCollection: (collection: Collection) -> Unit,
    onCollectionClick: (collection: Collection) -> Unit,
) {
    Dismissible(
        item = collection,
        dismissed = onDeleteCollection,
        directions = setOf(
            DismissDirection.EndToStart
        ),
        content = {
            CollectionItem(
                collection = collection,
                onClick = onCollectionClick
            )
        }
    )
}