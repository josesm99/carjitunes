package carlos.com.carjitunes.data.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "SongData", primaryKeys = ["trackId", "termSearched"])
@Parcelize
data class SongData(
    val wrapperType: String?,
    val kind: String?,
    val artistId: Int?,
    val collectionId: Int?,
    val trackId: Int,
    val artistName: String?,
    val collectionName: String?,
    val trackName: String?,
    val collectionCensoredName: String?,
    val trackCensoredName: String?,
    val artistViewUrl: String?,
    val collectionViewUrl: String?,
    val trackViewUrl: String?,
    val previewUrl: String?,
    val artworkUrl30: String?,
    val artworkUrl60: String?,
    val artworkUrl100: String?,
    val collectionPrice: Double?,
    val trackPrice: Double,
    val collectionHdPrice: Double?,
    val trackHdPrice: Double?,
    val releaseDate: String?,
    val collectionExplicitness: String?,
    val trackExplicitness: String?,
    val discCount: Int?,
    val discNumber: Int?,
    val trackCount: Int?,
    val trackNumber: Int?,
    val trackTimeMillis: Int?,
    val country: String?,
    val currency: String?,
    val primaryGenreName: String?,
    val contentAdvisoryRating: String?,
    val shortDescription: String?,
    val longDescription: String?
) : Parcelable {
    var termSearched: String = ""
}