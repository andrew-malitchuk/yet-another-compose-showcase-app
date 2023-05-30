package dev.yacsa.domain.impl.usecase.books

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.NewBookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.books.SubscribeToFavourite
import dev.yacsa.repository.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class SubscribeToFavouriteImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: NewBookDomainRepoMapper,
) : SubscribeToFavourite {

    override suspend fun invoke(): Either<DomainError, Flow<List<BookDomainModel?>?>> {
        return try {
            Either.Right(
                // TODO: fix
                booksRepository.subscribeFavourite().map {list->
                    list?.map {
                        it?.let { it1 -> bookDomainRepoMapper.toDomain(it1) }
                    }
                }
            )
        }catch (ex:Exception){
            Either.Left(DataError(ex))
        }
    }

}