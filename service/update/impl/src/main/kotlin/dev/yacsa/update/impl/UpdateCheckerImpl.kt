package dev.yacsa.update.impl

import dev.yacsa.update.UpdateChecker
import dev.yacsa.update.model.UpdateModel
import javax.inject.Inject

class UpdateCheckerImpl @Inject constructor() : UpdateChecker {

    override suspend fun check(): UpdateModel? {
        TODO("Not yet implemented")
    }

}