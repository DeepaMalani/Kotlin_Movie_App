package android.myapp.popularmovies.detail

import android.app.Application
import android.myapp.popularmovies.network.Movie
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Simple ViewModel factory that provides the movie and context to the ViewModel.
 */
class MovieDetailViewModelFactory(
    private val movie: Movie,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(movie, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
