package carlos.com.carjitunes.data.repository

import android.content.Context
import carlos.com.carjitunes.data.db.SearchSongsDao
import carlos.com.carjitunes.data.model.Result
import carlos.com.carjitunes.data.model.SongData
import carlos.com.carjitunes.data.model.SongPagingResponse
import carlos.com.carjitunes.data.network.ITunesSongsAppService
import carlos.com.carjitunes.data.util.NetworkManager.isOnline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val ITEMS_PER_PAGE = 20

class Repository(
    private val service: ITunesSongsAppService,
    private val context: Context,
    private val dao: SearchSongsDao
) {

    suspend fun insertSearchedSongs(term: String) {
        if (isOnline(context)) {
            insertUpdateDataFromNetwork(term)
        }
    }

    suspend fun getSongs(page: Int, term: String): SongPagingResponse {
        val searchData = obtainDataFromDatabase(page, term)
        return SongPagingResponse(
            page, term,
            if (searchData.isNotEmpty()) {
                searchData
            } else {
                emptyList()
            }
        )
    }

    private suspend fun insertUpdateDataFromNetwork(termSearched: String) {
        when (val result = service.getITunesSongs(termSearched)) {
            is Result.Success -> {
                if (!result.data.results.isNullOrEmpty()) {
                    withContext(Dispatchers.IO) {
                        dao.addSearchData(termSearched, result.data.results)
                    }
                }
            }
            is Result.Error -> {
                result.error.printStackTrace()
            }
        }
    }

    private suspend fun obtainDataFromDatabase(page: Int, term: String): List<SongData> {
        return withContext(Dispatchers.IO) {
            dao.findByTerm(term, ITEMS_PER_PAGE, ITEMS_PER_PAGE * (page - 1))
        }
    }
}