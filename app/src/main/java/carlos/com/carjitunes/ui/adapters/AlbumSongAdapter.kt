package carlos.com.carjitunes.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import carlos.com.carjitunes.R
import carlos.com.carjitunes.data.model.AlbumSongData
import kotlinx.android.synthetic.main.album_song_item.view.*

class AlbumSongAdapter : RecyclerView.Adapter<AlbumSongAdapter.AlbumSongHolder>()   {

    var data: List<AlbumSongData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(viewHolder: AlbumSongHolder, position: Int) {
        val albumSongData: AlbumSongData = data[position]
        viewHolder.itemView.text_track_name.text = albumSongData.trackName
        viewHolder.itemView.text_track_price.text = "$ ${albumSongData.trackPrice}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumSongHolder {
        return AlbumSongHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.album_song_item, parent, false)
        )
    }

    class AlbumSongHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount() = data.size
}