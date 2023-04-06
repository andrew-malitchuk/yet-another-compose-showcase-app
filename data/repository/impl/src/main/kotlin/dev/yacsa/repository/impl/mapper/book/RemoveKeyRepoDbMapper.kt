package dev.yacsa.repository.impl.mapper.book

import dev.yacsa.database.model.RemoteKeyDbModel
import dev.yacsa.repository.impl.mapper.base.RepoDbMapper
import dev.yacsa.repository.model.RemoteKeyRepoModel
import javax.inject.Inject

class RemoteKeyRepoDbMapper @Inject constructor(
) : RepoDbMapper<RemoteKeyRepoModel, RemoteKeyDbModel>() {

    override fun toRepo(value: RemoteKeyDbModel): RemoteKeyRepoModel {
        return RemoteKeyRepoModel(
            value.bookId,
            value.prevKey,
            value.currentPage,
            value.nextKey,
            value.createdAt
        )
    }

    override fun toDb(value: RemoteKeyRepoModel): RemoteKeyDbModel {
        return RemoteKeyDbModel(
            value.bookId,
            value.prevKey,
            value.currentPage,
            value.nextKey,
            value.createdAt
        )
    }

}