package android.myapp.popularmovies.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Movie (

    val id:Long ,
    val title:String ,
    @Json(name ="poster_path")
    val posterPath:String ) : Parcelable