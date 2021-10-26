package vander.gabriel.listpad.domain.entities

/**
 * A route enumeration that should contain all routes available on the app
 */
enum class NavigationRoutes(
    /**
     * The route name to go to
     */
    val route: String
) {
    /**
     * A rout to the main collection list screen
     */
    COLLECTION_LIST("collectionList");
}