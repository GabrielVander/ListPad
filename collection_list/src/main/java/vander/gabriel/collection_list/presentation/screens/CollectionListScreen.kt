package vander.gabriel.collection_list.presentation.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * The primary screen tasked with displaying all collections
 */
@Composable
fun CollectionListScreen() {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        Greeting("Android 2")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

/**
 * The default preview of the entire screen
 */
@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    CollectionListScreen()
}
