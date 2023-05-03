package dev.yacsa.domain.model

import dev.yacsa.domain.model.base.BaseDomainModel

data class BookDomainModel(
    val id: Int?,
    val title: String?,
    val subjects: List<String?>?,
    val authors: List<PersonDomainModel?>? = emptyList(),
    val translators: List<PersonDomainModel?>? = emptyList(),
    val bookshelves: List<String?>?,
    val languages: List<String?>?,
    val copyright: Boolean?,
    val mediaType: String?,
    val formats: FormatsDomainModel?,
    val downloadCount: Int?,
) : BaseDomainModel {
    var isCached: Boolean = false
}
