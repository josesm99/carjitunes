package carlos.com.carjitunes.di

import carlos.com.carjitunes.ui.vm.SongsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SongsViewModel(get()) }
}