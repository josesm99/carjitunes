package carlos.com.carjitunes.data.network.response

import android.os.Parcelable
import carlos.com.carjitunes.data.model.SongData
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class SongListResponse(
    @SerializedName("resultCount") val resultCount: Int? = null,
    @SerializedName("results") val results: List<SongData>
) : Parcelable