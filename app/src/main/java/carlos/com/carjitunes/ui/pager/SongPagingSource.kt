package carlos.com.carjitunes.ui.pager

import androidx.paging.PagingSource
import carlos.com.carjitunes.data.model.SongData
import carlos.com.carjitunes.data.repository.Repository

class SongPagingSource(
    private val repository: Repository,
    private val term: String
) : PagingSource<Int, SongData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SongData> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = repository.getSongs(nextPage, term)
            LoadResult.Page(
                data = movieListResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = movieListResponse.page?.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}