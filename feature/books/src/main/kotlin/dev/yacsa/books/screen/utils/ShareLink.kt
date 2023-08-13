package dev.yacsa.books.screen.utils

import android.content.Context
import android.content.Intent

fun Context.share(url: String, title: String) {
    this.startActivity(
        Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        },
    )
}
