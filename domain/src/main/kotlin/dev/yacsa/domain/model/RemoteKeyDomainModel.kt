package dev.yacsa.domain.model

import dev.yacsa.domain.model.base.BaseDomainModel

data class RemoteKeyDomainModel(
    val bookId: Int,
    val prevKey: Int?,
    val currentPage: Int,
    val nextKey: Int?,
    val createdAt: Long
) : BaseDomainModel