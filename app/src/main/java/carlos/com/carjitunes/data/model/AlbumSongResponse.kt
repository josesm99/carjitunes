package carlos.com.carjitunes.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumSongResponse(
    val resultCount: Int,
    val results: List<AlbumSongData>
) : Parcelable