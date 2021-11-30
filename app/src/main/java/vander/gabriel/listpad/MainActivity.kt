package vander.gabriel.listpad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.InternalCoroutinesApi
import vander.gabriel.listpad.domain.entities.NavigationRoutes
import vander.gabriel.listpad.presentation.screens.CollectionCreationScreen
import vander.gabriel.listpad.presentation.screens.CollectionListScreen
import vander.gabriel.listpad.presentation.theme.ListPadTheme

@InternalCoroutinesApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            ListPadTheme {
                MyApp()
            }
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterialApi
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
