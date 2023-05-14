package dev.yacsa.domain.impl.usecase.books

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.NewBookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.books.NewSaveBooksUseCase
import dev.yacsa.repository.repository.BooksRepository
import javax.inject.Inject

class NewSaveBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: NewBookDomainRepoMapper,
) : NewSaveBooksUseCase {
    override suspend fun invoke(page: Int, list: List<BookDomainModel>): Option<DomainError> {
        return try {
            booksRepository.removePage(page)
            booksRepository.savePaged(page, list.map { bookDomainRepoMapper.toRepo(it) })
            none()
        } catch (ex: Exception) {
            DataError(ex).some()
        }
    }
}
