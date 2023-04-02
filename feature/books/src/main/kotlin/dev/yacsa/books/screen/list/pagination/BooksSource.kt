package dev.yacsa.books.screen.list.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.GetBooksUseCase
import dev.yacsa.model.mapper.BookUiDomainMapper
import dev.yacsa.model.model.BookUiModel

class BooksSource(
    private val getBooksUseCase: GetBooksUseCase,
    private val bookUiDomainMapper: BookUiDomainMapper
) : PagingSource<Int, BookUiModel>() {

    override fun getRefreshKey(state: PagingState<Int, BookUiModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookUiModel> {
        return try {
            val nextPage = params.key ?: 1
            val response = getBooksUseCase(nextPage).map(bookUiDomainMapper::toUi)
            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}