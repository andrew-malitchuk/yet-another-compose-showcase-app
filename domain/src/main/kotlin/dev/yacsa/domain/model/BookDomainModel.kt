package dev.yacsa.domain.model

import dev.yacsa.domain.model.base.BaseDomainModel

data class BookDomainModel(
    val id: Int?,
    val title: String?,
    val subjects: List<String?>?,
    val authors: List<PersonDomainModel?>? = emptyList(),
    val copyright: Boolean?,
    val mediaType: String?,
    val formats: FormatsDomainModel? = FormatsDomainModel(),
    val downloadCount: Int?,
) : BaseDomainModel
