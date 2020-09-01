package carlos.com.carjitunes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import carlos.com.carjitunes.R
import carlos.com.carjitunes.data.model.SongData
import carlos.com.carjitunes.ui.adapters.AlbumSongAdapter
import carlos.com.carjitunes.ui.vm.SongsViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_album.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val SONG_DATA = "albumData"

class AlbumFragment : Fragment() {

    private val viewModel: SongsViewModel by viewModel()

    private lateinit var albumSongAdapter: AlbumSongAdapter

    private var songData: SongData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            songData = bundle.getParcelable(SONG_DATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
        initViews(view)
        viewModel.getITunesAlbumSongs(songData!!.collectionId.toString())
    }

    private fun initObservables() {
        viewModel.albumSongsListLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                albumSongAdapter.data = it.results
                flipper_songs.displayedChild = 1
            }
        })
    }

    private fun initViews(view: View) {
        flipper_songs.displayedChild = 0
        initData()
        initToolbar(view)
        initRecycler()
    }

    private fun initData() {
        artist.text = songData!!.artistName
        Glide.with(requireContext())
            .load(songData!!.artworkUrl100)
            .into(smallicon)
    }

    private fun initToolbar(view: View) {
        main_toolbar.setNavigationIcon(R.drawable.ic_back_24);
        main_toolbar.setNavigationOnClickListener{ findNavController().navigateUp() }
    }

    private fun initRecycler() {
        albumSongAdapter = AlbumSongAdapter()
        album_song_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = albumSongAdapter
        }
    }

}