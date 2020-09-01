package carlos.com.carjitunes.data.db

import androidx.room.*
import carlos.com.carjitunes.data.model.SongData

@Dao
interface SearchSongsDao {

    @Query("SELECT * FROM SongData WHERE termSearched = :termSearched LIMIT :itemsPerPage OFFSET :offset ")
    fun findByTerm(termSearched: String, itemsPerPage: Int, offset: Int): List<SongData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSong(song: SongData)

    @Transaction
    fun addSearchData(
        term: String,
        songs: List<SongData>
    ) {
        songs.forEach {
            it.termSearched = term
            val res = addSong(it)
            res
        }
    }
}