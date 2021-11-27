package vander.gabriel.listpad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vander.gabriel.listpad.domain.entities.NavigationRoutes
import vander.gabriel.listpad.presentation.screens.CollectionCreationScreen
import vander.gabriel.listpad.presentation.screens.CollectionListScreen
import vander.gabriel.listpad.presentation.theme.ListPadTheme

/**
 * The app's only activity
 */
class MainActivity : ComponentActivity() {
    /**
     * The app's initial structure
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListPadTheme {
                MyApp()
            }
        }
    }
}

/**
 * The main app composable
 */
@Composable
fun MyApp() {
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = NavigationRoutes.COLLECTION_LIST.route
    ) {
        composable(NavigationRoutes.COLLECTION_LIST.route) {
            CollectionListScreen(navigationController = navigationController)
        }
        composable(NavigationRoutes.COLLECTION_CREATION.route) {
            CollectionCreationScreen(navigationController = navigationController)
        }
    }
}
