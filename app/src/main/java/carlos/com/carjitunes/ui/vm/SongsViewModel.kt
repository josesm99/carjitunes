package carlos.com.carjitunes.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import carlos.com.carjitunes.data.repository.Repository
import carlos.com.carjitunes.ui.pager.SongPagingSource
import kotlinx.coroutines.launch

class SongsViewModel(private val repository: Repository) : ViewModel() {

    val saveSearch = MutableLiveData<Boolean>()

    fun saveSeachedSongs(terms: String) {
        viewModelScope.launch {
            repository.insertSearchedSongs(terms)
            saveSearch.postValue(true)
        }
    }

    fun createPagingDataFlow(terms: String) =
        Pager(PagingConfig(20)) {
            SongPagingSource(repository, terms)
        }.flow
}
