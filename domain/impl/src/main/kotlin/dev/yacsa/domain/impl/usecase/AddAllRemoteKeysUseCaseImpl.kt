package dev.yacsa.domain.impl.usecase

import dev.yacsa.domain.impl.mapper.RemoteKeyDomainRepoMapper
import dev.yacsa.domain.model.RemoteKeyDomainModel
import dev.yacsa.domain.usecase.AddAllRemoteKeysUseCase
import dev.yacsa.repository.BooksRepository
import javax.inject.Inject

class AddAllRemoteKeysUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val remoteKeyDomainRepoMapper: RemoteKeyDomainRepoMapper
) : AddAllRemoteKeysUseCase {
    override suspend fun invoke(remoteKeys: List<RemoteKeyDomainModel?>) {
        booksRepository.insertAll(remoteKeys.filterNotNull().map { remoteKeyDomainRepoMapper.toRepo(it) })
    }

}