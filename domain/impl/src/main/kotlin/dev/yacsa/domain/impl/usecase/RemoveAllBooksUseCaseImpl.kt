package dev.yacsa.domain.impl.usecase

import dev.yacsa.domain.usecase.RemoveAllBooksUseCase
import dev.yacsa.repository.BooksRepository
import javax.inject.Inject

class RemoveAllBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
) : RemoveAllBooksUseCase {
    override suspend fun invoke() {
        booksRepository.removeAll()
    }

}