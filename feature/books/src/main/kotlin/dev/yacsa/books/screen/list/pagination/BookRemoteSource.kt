package dev.yacsa.books.screen.list.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import coil.network.HttpException
import dev.yacsa.domain.model.RemoteKeyDomainModel
import dev.yacsa.domain.usecase.*
import dev.yacsa.model.model.BookUiModel
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BookRemoteSource(
    private val initialPage: Int = 1,
    private var getBooksUseCase: GetBooksUseCase,
    private var getRemoteKeyByBookId: GetRemoteKeyByBookId,
    private var removeRemoteKeyUseCase: RemoveRemoteKeyUseCase,
    private var removeAllBooksUseCase: RemoveAllBooksUseCase,
    private var addAllRemoteKeysUseCase: AddAllRemoteKeysUseCase,
    private var saveBooksUseCase: SaveBooksUseCase,

    ) : RemoteMediator<Int, BookUiModel>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, BookUiModel>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                //New Query so clear the DB
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                // If remoteKeys is null, that means the refresh result is not in the database yet.
                val prevKey = remoteKeys?.prevKey
                prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)

                // If remoteKeys is null, that means the refresh result is not in the database yet.
                // We can return Success with endOfPaginationReached = false because Paging
                // will call this method again if RemoteKeys becomes non-null.
                // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                // the end of pagination for append.
                val nextKey = remoteKeys?.nextKey
                nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }
        try {
            val apiResponse = getBooksUseCase(page = page)

            val endOfPaginationReached = apiResponse.isEmpty()

            if (loadType == LoadType.REFRESH) {
                removeRemoteKeyUseCase()
                removeAllBooksUseCase()
            }
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (endOfPaginationReached) null else page + 1
            val remoteKeys = apiResponse.map {book->
                book.id?.let { it ->
                    RemoteKeyDomainModel(
                        bookId = it,
                        prevKey = prevKey,
                        currentPage = page,
                        nextKey = nextKey,
                        createdAt = System.currentTimeMillis()
                    )
                }
            }
            addAllRemoteKeysUseCase(remoteKeys)
            saveBooksUseCase(apiResponse)

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }

    /** LoadType.Append
     * When we need to load data at the end of the currently loaded data set, the load parameter is LoadType.APPEND
     */
    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, BookUiModel>): RemoteKeyDomainModel? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { item ->
//            moviesDatabase.getRemoteKeysDao().getRemoteKeyByMovieID(movie.id)
            item.id?.let { getRemoteKeyByBookId(it) }
        }
    }

    /** LoadType.Prepend
     * When we need to load data at the beginning of the currently loaded data set, the load parameter is LoadType.PREPEND
     */
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, BookUiModel>): RemoteKeyDomainModel? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { item ->
//            moviesDatabase.getRemoteKeysDao().getRemoteKeyByMovieID(movie.id)
            item.id?.let { getRemoteKeyByBookId(it) }
        }
    }

    /** LoadType.REFRESH
     * Gets called when it's the first time we're loading data, or when PagingDataAdapter.refresh() is called;
     * so now the point of reference for loading our data is the state.anchorPosition.
     * If this is the first load, then the anchorPosition is null.
     * When PagingDataAdapter.refresh() is called, the anchorPosition is the first visible position in the displayed list, so we will need to load the page that contains that specific item.
     */
    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, BookUiModel>): RemoteKeyDomainModel? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
//                moviesDatabase.getRemoteKeysDao().getRemoteKeyByMovieID(id)
                getRemoteKeyByBookId(id)

            }
        }
    }


}