package carlos.com.carjitunes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import carlos.com.carjitunes.data.model.SongData

@Database(
    entities = [SongData::class],
    version = 1, exportSchema = false
)
abstract class SearchSongsDatabase : RoomDatabase() {
    abstract val searchSongsDao: SearchSongsDao
}