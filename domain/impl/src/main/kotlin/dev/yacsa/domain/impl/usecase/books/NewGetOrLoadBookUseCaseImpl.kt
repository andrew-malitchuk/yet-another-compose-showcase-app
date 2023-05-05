package dev.yacsa.domain.impl.usecase.books

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.BookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.books.NewGetOrLoadBookUseCase
import dev.yacsa.repository.repository.BooksRepository
import javax.inject.Inject

class NewGetOrLoadBookUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: BookDomainRepoMapper,
) : NewGetOrLoadBookUseCase {

    override suspend fun invoke(bookId: Int): Either<DomainError, BookDomainModel> {
        return try {
            val fromNet = booksRepository.getBook(bookId)
            if (fromNet == null) {
                Either.Left(dev.yacsa.domain.error.NoDataError)
            } else {
                try {
                    bookDomainRepoMapper.toDomain(fromNet)
                    booksRepository.saveBook(fromNet)
                    Either.Right(bookDomainRepoMapper.toDomain(fromNet))
                } catch (ex: Exception) {
                    Either.Left(DataError(ex))
                }
            }
        } catch (ex: Exception) {
            try {
                val result =
                    booksRepository.loadBook(bookId)?.let { bookDomainRepoMapper.toDomain(it) }
                if (result == null) {
                    Either.Left(dev.yacsa.domain.error.NoDataError)
                } else {
                    Either.Right(result)
                }
            } catch (ex: Exception) {
                Either.Left(DataError(ex))
            }
        }

    }
}