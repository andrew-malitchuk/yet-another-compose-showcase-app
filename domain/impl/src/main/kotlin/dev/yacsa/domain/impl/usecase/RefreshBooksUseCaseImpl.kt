package dev.yacsa.domain.impl.usecase

import dev.yacsa.domain.usecase.RefreshBooksUseCase
import dev.yacsa.platform.ext.resultOf
import dev.yacsa.repository.BooksRepository
import javax.inject.Inject

class RefreshBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
) : RefreshBooksUseCase {

    override suspend fun invoke(): Result<Unit> {
        return resultOf {
            booksRepository.refreshBooks()
        }
    }
}
