package dev.yacsa.main.tile

import android.content.Intent
import android.service.quicksettings.TileService
import dev.yacsa.main.screen.main.MainActivity

class FavouriteTileService : TileService(){

    // Called when the user adds your tile.
    override fun onTileAdded() {
        super.onTileAdded()
    }
    // Called when your app can update your tile.
    override fun onStartListening() {
        super.onStartListening()
    }

    // Called when your app can no longer update your tile.
    override fun onStopListening() {
        super.onStopListening()
    }

    // Called when the user taps on your tile in an active or inactive state.
    override fun onClick() {
        super.onClick()
        startActivityAndCollapse(
            Intent(
                this,
                MainActivity::class.java
            ).also {
                it.flags=Intent.FLAG_ACTIVITY_NEW_TASK
                it.putExtra("deeplink","yacsa://favourite")
            }
        )
    }
    // Called when the user removes your tile.
    override fun onTileRemoved() {
        super.onTileRemoved()
    }

}