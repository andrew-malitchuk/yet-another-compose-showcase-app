package dev.yacsa.repository.model

import dev.yacsa.network.model.FormatsNetModel
import dev.yacsa.network.model.PersonNetModel
import dev.yacsa.repository.model.base.BaseRepoModel

data class BookRepoModel(
    val id: Int?,
    val title: String?,
    val subjects: List<String?>?,
//    val authors: List<PersonNetModel?>?,
//    val translators: List<PersonNetModel?>?,
//    val bookshelves: List<String?>?,
//    val languages: List<String?>?,
    val copyright: Boolean?,
    val mediaType: String?,
//    val formats: FormatsNetModel?,
    val downloadCount: Int?,
) : BaseRepoModel