package android.myapp.popularmovies

import android.myapp.popularmovies.network.Movie
import android.myapp.popularmovies.overview.PhotoGridAdapter
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

const val BASE_URL = "http://image.tmdb.org/t/p/"
const val  SIZE = "w185"

//Use Glide library to load an image into imageView
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl:String?){
    imageUrl?.let {

        //It converts image url to Image URI
        val imgUri = (BASE_URL + SIZE + imageUrl).toUri().buildUpon().scheme("https").build()

        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imageView)

    }

}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}