package vander.gabriel.listpad.domain.entities

enum class NavigationRoutes(
    val route: String,
) {
    COLLECTION_LIST("collectionList"),

    COLLECTION_CREATION("collectionCreation"),

    COLLECTION_DETAILS("collectionDetails/{collectionId}");
}