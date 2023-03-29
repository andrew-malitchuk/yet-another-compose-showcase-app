package dev.yacsa.domain.model

import dev.yacsa.domain.model.base.BaseDomainModel

data class BookDomainModel(
    val id: Int?,
    val title: String?,
    val subjects: List<String?>?,
    val copyright: Boolean?,
    val mediaType: String?,
    val downloadCount: Int?,
) : BaseDomainModel