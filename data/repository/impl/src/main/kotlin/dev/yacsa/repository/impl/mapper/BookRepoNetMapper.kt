package dev.yacsa.repository.impl.mapper

import dev.yacsa.datastore.model.StartUpConfigureDataStoreModel
import dev.yacsa.network.model.BookNetModel
import dev.yacsa.network.model.base.BaseNetModel
import dev.yacsa.repository.impl.mapper.base.RepoNetMapper
import dev.yacsa.repository.model.BookRepoModel
import dev.yacsa.repository.model.StartUpConfigureRepoModel
import javax.inject.Inject

class BookRepoNetMapper @Inject constructor() :
    RepoNetMapper<BookRepoModel, BookNetModel>() {

    override fun toRepo(value: BookNetModel): BookRepoModel {
        return BookRepoModel(
            value.id,
            value.title,
            value.subjects,
            value.copyright,
            value.mediaType,
            value.downloadCount
        )
    }

    // TODO: fix
    override fun toNet(value: BookRepoModel): BookNetModel {
        return BookNetModel(
            value.id,
            value.title,
            value.subjects,
            null,
            null,
            null,
            null,
            value.copyright,
            value.mediaType,
            null,
            value.downloadCount
        )
    }


}