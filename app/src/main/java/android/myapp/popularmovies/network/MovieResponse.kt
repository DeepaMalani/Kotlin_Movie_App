package android.myapp.popularmovies.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(

    val page : Int,
    @Json(name = "total_results")
    val totalResults : Long,
    @Json(name = "total_pages")
    val totalPages : Long ,
    @Json(name = "results")
    val MovieProperties : List<Movie>
)

