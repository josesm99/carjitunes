package carlos.com.carjitunes

import android.app.Application
import carlos.com.carjitunes.di.databaseModule
import carlos.com.carjitunes.di.networkModule
import carlos.com.carjitunes.di.repositoryModule
import carlos.com.carjitunes.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SongApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SongApp)
            modules(listOf(networkModule, repositoryModule, viewModelModule, databaseModule))
        }
    }
}