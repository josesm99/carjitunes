package carlos.com.carjitunes.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import carlos.com.carjitunes.R
import carlos.com.carjitunes.data.model.SongData
import kotlinx.android.synthetic.main.song_item.view.*

class SongAdapter(
    private val listener: (SongData) -> Unit
) : PagingDataAdapter<SongData, RecyclerView.ViewHolder>(
    SongModelComparator
) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val songData: SongData = getItem(position)!!

        val viewHolder = holder as SongViewHolder
        viewHolder.itemView.song_title.text = songData.trackName
        viewHolder.itemView.song_price.text = songData.trackPrice.toString()

        viewHolder.itemView.view_song_item.setOnClickListener {
            listener.invoke(songData)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SongViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.song_item, parent, false)
        )
    }

    companion object {
        private val SongModelComparator = object : DiffUtil.ItemCallback<SongData>() {
            override fun areItemsTheSame(oldItem: SongData, newItem: SongData) =
                oldItem.trackId == newItem.trackId


            override fun areContentsTheSame(oldItem: SongData, newItem: SongData) =
                oldItem == newItem
        }
    }

    class SongViewHolder(view: View) : RecyclerView.ViewHolder(view)
}