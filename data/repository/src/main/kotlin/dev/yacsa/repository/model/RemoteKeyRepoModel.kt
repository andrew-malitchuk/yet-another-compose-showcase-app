package dev.yacsa.repository.model

import dev.yacsa.repository.model.base.BaseRepoModel

data class RemoteKeyRepoModel(
    val bookId: Int,
    val prevKey: Int?,
    val currentPage: Int,
    val nextKey: Int?,
    val createdAt: Long
) : BaseRepoModel