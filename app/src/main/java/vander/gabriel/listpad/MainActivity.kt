package vander.gabriel.listpad

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.InternalCoroutinesApi
import vander.gabriel.listpad.domain.entities.NavigationRoutes
import vander.gabriel.listpad.presentation.screens.COLLECTION_ARGUMENT_KEY
import vander.gabriel.listpad.presentation.screens.CollectionCreationScreen
import vander.gabriel.listpad.presentation.screens.CollectionDetailsScreen
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
    val tag = "MyApp"

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
        composable(route = NavigationRoutes.COLLECTION_DETAILS.route,
            arguments = listOf(navArgument(COLLECTION_ARGUMENT_KEY) {
                type = NavType.StringType
            })) { navBackStackEntry ->
            val collectionId: String? =
                navBackStackEntry.arguments?.getString(COLLECTION_ARGUMENT_KEY)

            if (collectionId != null) {
                CollectionDetailsScreen(collectionId = collectionId)
            } else {
                Log.w(tag, "Missing collectionId argument")
            }
        }
    }
}
