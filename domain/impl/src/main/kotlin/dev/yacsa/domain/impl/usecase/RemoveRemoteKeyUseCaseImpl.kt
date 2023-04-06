package dev.yacsa.domain.impl.usecase

import dev.yacsa.domain.impl.mapper.BookDomainRepoMapper
import dev.yacsa.domain.usecase.RemoveRemoteKeyUseCase
import dev.yacsa.repository.BooksRepository
import javax.inject.Inject

class RemoveRemoteKeyUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: BookDomainRepoMapper
) : RemoveRemoteKeyUseCase {
    override suspend fun invoke() {
        booksRepository.clearRemoteKeys()
    }

}