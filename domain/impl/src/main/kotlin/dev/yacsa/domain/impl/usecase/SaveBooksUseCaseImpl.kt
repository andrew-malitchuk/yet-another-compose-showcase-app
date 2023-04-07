package dev.yacsa.domain.impl.usecase

import dev.yacsa.domain.impl.mapper.BookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.SaveBooksUseCase
import dev.yacsa.repository.BooksRepository
import javax.inject.Inject

class SaveBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: BookDomainRepoMapper
) : SaveBooksUseCase {
    override suspend fun invoke(list: List<BookDomainModel>) {
        booksRepository.saveBooks(list.map { bookDomainRepoMapper.toRepo(it) })
    }
}