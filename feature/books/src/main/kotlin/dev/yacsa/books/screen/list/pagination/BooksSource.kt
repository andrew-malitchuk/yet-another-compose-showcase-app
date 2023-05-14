package dev.yacsa.books.screen.list.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.usecase.books.NewGetBooksUseCase
import dev.yacsa.domain.usecase.books.NewLoadBooksUseCase
import dev.yacsa.domain.usecase.books.NewSaveBooksUseCase
import dev.yacsa.model.mapper.NewBooksUiDomainMapper
import dev.yacsa.model.model.BookUiModel

class BooksSource(
    private val getBooksUseCase: NewGetBooksUseCase,
    private val loadBooksUseCase: NewLoadBooksUseCase,
    private val bookUiDomainMapper: NewBooksUiDomainMapper,
    private val saveBooksUseCase: NewSaveBooksUseCase,
) : PagingSource<Int, BookUiModel>() {

    override fun getRefreshKey(state: PagingState<Int, BookUiModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookUiModel> {
        val nextPage = params.key ?: 1
        val fromNet = getBooksUseCase(nextPage)
        return fromNet.fold(
            {
                loadBooksUseCase(nextPage).fold(
                    { error ->
                        when (error) {
                            is DataError -> {
                                LoadResult.Error(error.throwable)
                            }

                            else -> {
                                LoadResult.Error(Exception("SWW"))
                            }
                        }
                    },
                    { result ->
                        LoadResult.Page(
                            data = result.map(bookUiDomainMapper::toUi),
                            prevKey = if (nextPage == 1) null else nextPage - 1,
                            nextKey = if (result.isEmpty()) null else nextPage + 1,
                        )
                    },
                )
            },
            { result ->
                if (result.isNotEmpty()) {
                    saveBooksUseCase(nextPage, result).fold(
                        {
                            LoadResult.Page(
                                data = result.map(bookUiDomainMapper::toUi),
                                prevKey = if (nextPage == 1) null else nextPage - 1,
                                nextKey = if (result.isEmpty()) null else nextPage + 1,
                            )
                        },
                        { error ->
                            when (error) {
                                is DataError -> {
                                    LoadResult.Error(error.throwable)
                                }

                                else -> {
                                    LoadResult.Error(Exception("SWW"))
                                }
                            }
                        },
                    )
                } else {
                    LoadResult.Error(Exception("No data"))
                }
            },
        )
    }
}
