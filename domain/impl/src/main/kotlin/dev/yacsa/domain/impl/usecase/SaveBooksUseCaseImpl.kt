package dev.yacsa.domain.impl.usecase

import dev.yacsa.domain.impl.mapper.NewBookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.books.SaveBooksUseCase
import dev.yacsa.repository.repository.BooksRepository
import javax.inject.Inject

class SaveBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: NewBookDomainRepoMapper,
) : SaveBooksUseCase {
    override suspend fun invoke(page: Int, list: List<BookDomainModel>) {
        booksRepository.removePage(page)
        booksRepository.savePaged(page, list.map { bookDomainRepoMapper.toRepo(it) })
    }
}
