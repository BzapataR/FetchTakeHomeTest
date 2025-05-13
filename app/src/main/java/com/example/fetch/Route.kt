package com.example.fetch

import kotlinx.serialization.Serializable
@Serializable
sealed class Route(val route:String) { // Route class to add more navigation routes
    @Serializable
    data object ListGraph: Route("listGraph")
    @Serializable
    data object ListScreen: Route("listScreen")

}