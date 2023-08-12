package dev.yacsa.info.navigation

sealed class InfoDirections(var route: String) {
    object Info : InfoDirections("info_list")
}
