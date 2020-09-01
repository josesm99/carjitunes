package carlos.com.carjitunes.data.network

import carlos.com.carjitunes.data.model.Result
import carlos.com.carjitunes.data.network.response.SongListResponse

class ITunesSongsAppService(private val api: Api) : BaseService() {

    suspend fun getITunesSongs(term: String): Result<SongListResponse> {
        return createCall {
            val response = api.getITunesSongs(term)
            response
        }
    }

}