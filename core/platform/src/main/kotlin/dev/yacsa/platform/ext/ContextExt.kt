package dev.yacsa.platform.ext

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

@Suppress("TooGenericExceptionCaught", "SwallowedException")
fun Context.triggerDeepLink(link: String, isNewTask: Boolean = false) {
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            if (isNewTask) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            data = Uri.parse(link)
            setPackage(packageName)
        }
        startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(this, "This link is not handled by your App!", Toast.LENGTH_SHORT).show()
    }
}
