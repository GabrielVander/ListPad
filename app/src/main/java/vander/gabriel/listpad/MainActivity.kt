package vander.gabriel.listpad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vander.gabriel.listpad.domain.models.NavigationRoutes
import vander.gabriel.listpad.presentation.screens.CollectionListScreen
import vander.gabriel.listpad.presentation.theme.ListPadTheme

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

    NavHost(
        navController = navigationController,
        startDestination = NavigationRoutes.COLLECTION_LIST.route
    ) {
        composable(NavigationRoutes.COLLECTION_LIST.route) {
            CollectionListScreen()
        }
    }
}
