package carlos.com.carjitunes.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SongPagingResponse(
    val page: Int,
    val term: String,
    val results: List<SongData>
) : Parcelable