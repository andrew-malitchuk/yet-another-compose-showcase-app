package dev.yacsa.update

import dev.yacsa.update.model.UpdateModel

fun interface UpdateChecker {
    suspend fun check(): UpdateModel?
}