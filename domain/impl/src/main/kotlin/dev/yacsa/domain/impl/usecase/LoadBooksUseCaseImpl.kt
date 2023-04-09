package dev.yacsa.domain.impl.usecase

import dev.yacsa.domain.impl.mapper.BookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.LoadBooksUseCase
import dev.yacsa.platform.ext.resultOf
import dev.yacsa.repository.BooksRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import javax.inject.Inject

class LoadBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: BookDomainRepoMapper,
) : LoadBooksUseCase {
    override suspend fun invoke(): Flow<Result<List<BookDomainModel>>> {
        return booksRepository
            .loadBooks()
            .map { list ->
                resultOf {
                    list.map(bookDomainRepoMapper::toDomain)
                }
            }
            .retryWhen { cause, _ ->
                if (cause is IOException) {
                    emit(Result.failure(cause))
                    // TODO: fix
                    delay(5_000L)
                    true
                } else {
                    false
                }
            }
            .catch {
                emit(Result.failure(it))
            }
    }
}
