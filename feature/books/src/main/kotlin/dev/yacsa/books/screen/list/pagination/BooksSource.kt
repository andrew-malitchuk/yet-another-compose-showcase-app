package dev.yacsa.books.screen.list.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.yacsa.domain.usecase.books.GetBooksUseCase
import dev.yacsa.domain.usecase.books.LoadBooksUseCase
import dev.yacsa.domain.usecase.books.SaveBooksUseCase
import dev.yacsa.model.mapper.BookUiDomainMapper
import dev.yacsa.model.model.BookUiModel
import java.io.IOException

class BooksSource(
    private val getBooksUseCase: GetBooksUseCase,
    private val loadBooksUseCase: LoadBooksUseCase,
    private val bookUiDomainMapper: BookUiDomainMapper,
    private val saveBooksUseCase: SaveBooksUseCase,
) : PagingSource<Int, BookUiModel>() {

    override fun getRefreshKey(state: PagingState<Int, BookUiModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookUiModel> {
        return try {
            val nextPage = params.key ?: 1
            val list = try {
                val fromNet = getBooksUseCase(nextPage)
                if (fromNet.isNotEmpty()) {
                    saveBooksUseCase(nextPage, fromNet)
                }
                fromNet.map(bookUiDomainMapper::toUi)
            } catch (e: IOException) {
                loadBooksUseCase(nextPage).map(bookUiDomainMapper::toUi)
            }

            LoadResult.Page(
                data = list,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (list.isEmpty()) null else nextPage + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
