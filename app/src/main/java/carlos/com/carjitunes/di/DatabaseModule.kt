package carlos.com.carjitunes.di

import android.app.Application
import androidx.room.Room
import carlos.com.carjitunes.data.db.SearchSongsDao
import carlos.com.carjitunes.data.db.SearchSongsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): SearchSongsDatabase {
       return Room.databaseBuilder(application, SearchSongsDatabase::class.java, "songs")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideSearchSongsDao(database:  SearchSongsDatabase): SearchSongsDao {
        return  database.searchSongsDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideSearchSongsDao(get()) }
}
