package carlos.com.carjitunes.di

import android.content.Context
import carlos.com.carjitunes.data.db.SearchSongsDao
import carlos.com.carjitunes.data.network.ITunesSongsAppService
import carlos.com.carjitunes.data.repository.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { createRepository(get(), androidContext(), get()) }
}

fun createRepository(
    movieAppService: ITunesSongsAppService,
    context: Context,
    dao: SearchSongsDao
): Repository = Repository(movieAppService, context, dao)