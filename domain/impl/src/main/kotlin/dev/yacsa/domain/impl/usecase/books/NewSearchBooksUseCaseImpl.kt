package dev.yacsa.domain.impl.usecase.books

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.error.NoDataError
import dev.yacsa.domain.impl.mapper.NewBookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.books.NewSearchBooksUseCase
import dev.yacsa.repository.repository.BooksRepository
import javax.inject.Inject

class NewSearchBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: NewBookDomainRepoMapper,
) : NewSearchBooksUseCase {

    override suspend fun invoke(
        query: String,
        sort: String?,
        lang: String?
    ): Either<DomainError, List<BookDomainModel>> {
        return try {
            val fromNet = booksRepository.searchOnRemote(query, sort, lang)
            if (fromNet.isEmpty()) {
                Either.Left(NoDataError)
            } else {
                Either.Right(fromNet.map(bookDomainRepoMapper::toDomain))
            }
        } catch (ex: Exception) {
            try {
                val fromDb = booksRepository.searchOnLocal(query,sort,lang)
                if (fromDb.isEmpty()) {
                    Either.Left(NoDataError)
                } else {
                    Either.Right(fromDb.map(bookDomainRepoMapper::toDomain))
                }
            } catch (ex: Exception) {
                Either.Left(DataError(ex))
            }
        }
    }
}
