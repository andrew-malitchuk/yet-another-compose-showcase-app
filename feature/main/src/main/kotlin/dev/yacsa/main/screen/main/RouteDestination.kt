package dev.yacsa.main.screen.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// enum class RouteDestination {
//    ONBOARDING,
//    MAIN,
//    NI,
//    DEEPLINK
// }

@Parcelize
sealed class RouteDestination : Parcelable {
    object Onboarding : RouteDestination()
    object Main : RouteDestination()
    class Deeplink(val deeplink: String) : RouteDestination()
}
