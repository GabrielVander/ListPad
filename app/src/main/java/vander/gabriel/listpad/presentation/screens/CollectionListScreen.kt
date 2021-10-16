package vander.gabriel.listpad.presentation.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

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

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    CollectionListScreen()
}
