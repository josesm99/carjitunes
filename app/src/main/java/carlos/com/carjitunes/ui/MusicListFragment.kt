package carlos.com.carjitunes.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import carlos.com.carjitunes.R
import carlos.com.carjitunes.data.model.SongData
import carlos.com.carjitunes.extensions.hideKeyboard
import carlos.com.carjitunes.ui.adapters.SongAdapter
import carlos.com.carjitunes.ui.adapters.SongLoadStateAdapter
import carlos.com.carjitunes.ui.vm.SongsViewModel
import kotlinx.android.synthetic.main.fragment_music_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MusicListFragment : Fragment() {

    private val viewModel: SongsViewModel by viewModel()

    private lateinit var songAdapter: SongAdapter

    private var currentTerm: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_music_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
        initViews(view)
    }

    private fun initViews(view: View) {
        initToolbar(view)
        initRecycler()
    }

    private fun initRecycler() {
        songAdapter = SongAdapter(::onClickSongItem)

        song_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)

            adapter = songAdapter.withLoadStateFooter(
                footer = SongLoadStateAdapter { songAdapter.retry() }
            )
        }

        songAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE

                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }

                errorState?.let {
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun onClickSongItem(songData: SongData) {
        findNavController().navigate(
            R.id.action_musicListFragment_to_albumFragment,
            bundleOf(SONG_DATA to songData)
        )
    }

    private fun initToolbar(view: View) {
        val topAppBar = view.findViewById<Toolbar>(R.id.topAppBar)

        val searchView = topAppBar.menu.findItem(R.id.search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                Log.i("onQueryTextChange", newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                currentTerm = query
                viewModel.saveSeachedSongs(query)
                progressBar.visibility = View.VISIBLE

                hideKeyboard()
                return true
            }

        })
    }

    private fun initObservables() {
        viewModel.saveSearch.observe(viewLifecycleOwner, Observer {
            it?.let {
                lifecycleScope.launch {
                    songAdapter.refresh()
                    viewModel.createPagingDataFlow(currentTerm!!).collectLatest {
                        songAdapter.submitData(it)
                    }
                }
            }
        })
    }
}