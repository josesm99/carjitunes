package carlos.com.carjitunes.data.network

import carlos.com.carjitunes.data.model.AlbumSongResponse
import carlos.com.carjitunes.data.network.response.SongListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("search?mediaType=music&limit=200")
    suspend fun getITunesSongs(
        @Query("term") term: String
    ): Response<SongListResponse>

    @GET("lookup?country=us&entity=song")
    suspend fun getITunesAlbumSongs(
        @Query("id") albumId: String
    ): Response<AlbumSongResponse>
}