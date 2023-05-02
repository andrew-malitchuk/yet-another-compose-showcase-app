package dev.yacsa.model.model

import android.os.Parcelable
import dev.yacsa.model.model.base.BaseUiModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookUiModel(
    val id: Int?,
    val title: String?,
    val subjects: List<String?>?,
    val authors: List<PersonUiModel?>?,
    val translators: List<PersonUiModel?>?,
    val bookshelves: List<String?>?,
    val languages: List<String?>?,
    val copyright: Boolean?,
    val mediaType: String?,
    val formats: FormatsUiModel? = FormatsUiModel(),
    val downloadCount: Int?,
) : BaseUiModel, Parcelable{
    var isCached:Boolean=false
}
