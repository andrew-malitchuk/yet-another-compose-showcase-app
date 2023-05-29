package dev.yacsa.domain.impl.usecase.books

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.usecase.books.MarkFavouriteBook
import dev.yacsa.repository.repository.BooksRepository
import javax.inject.Inject


class MarkFavouriteBookImpl @Inject constructor(
    private val booksRepository: BooksRepository,
) : MarkFavouriteBook {

    override suspend fun invoke(id: Int, isFavourite: Boolean): Option<DomainError> {
        return try {
            booksRepository.markFavourite(id, isFavourite)
            none()
        } catch (ex: Exception) {
            DataError(ex).some()
        }
    }

}