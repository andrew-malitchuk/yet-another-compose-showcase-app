package dev.yacsa.notfound.navigation

sealed class NotFoundDirections(var route: String) {
    object NotFound : NotFoundDirections("nf_404")
}
