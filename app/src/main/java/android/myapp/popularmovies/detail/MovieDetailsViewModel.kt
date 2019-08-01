package android.myapp.popularmovies.detail

import android.app.Application
import android.myapp.popularmovies.network.Movie
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MovieDetailsViewModel(movie:Movie,app:Application) : AndroidViewModel(app){

  private val _selectedMovie = MutableLiveData<Movie>()

  val selectedMovie : LiveData<Movie>
    get() = _selectedMovie

    init {
      _selectedMovie.value = movie
    }
}