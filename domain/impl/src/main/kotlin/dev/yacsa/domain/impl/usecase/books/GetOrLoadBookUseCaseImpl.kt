package dev.yacsa.domain.impl.usecase.books

import dev.yacsa.domain.impl.mapper.NewBookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.books.GetOrLoadBookUseCase
import dev.yacsa.repository.repository.BooksRepository
import java.io.IOException
import javax.inject.Inject

class GetOrLoadBookUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: NewBookDomainRepoMapper,
) : GetOrLoadBookUseCase {
    @Throws(Throwable::class)
    override suspend fun invoke(bookId: Int): BookDomainModel? {
        val book = try {
            val fromNet = booksRepository.getBook(bookId)?.let { bookDomainRepoMapper.toDomain(it) }
            fromNet?.let {
                booksRepository.saveBook(bookDomainRepoMapper.toRepo(it))
            }
            fromNet
        } catch (e: IOException) {
            booksRepository.loadBook(bookId)?.let { bookDomainRepoMapper.toDomain(it) }
        }
        return book
    }
}
