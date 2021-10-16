package vander.gabriel.listpad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vander.gabriel.listpad.ui.theme.ListPadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListPadTheme {
                MyApp()
            }
        }
    }


}

@Composable
fun MyApp() {
    val navigationController = rememberNavController()

    // A surface container using the 'background' color from the theme
    NavHost(
        navController = navigationController,
        startDestination = NavigationRoutes.COLLECTION_LIST.route
    ) {
        composable(NavigationRoutes.COLLECTION_LIST.route) {
            CollectionListScreen()
        }
    }
}

@Composable
fun CollectionListScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListPadTheme {
        MyApp()
    }
}