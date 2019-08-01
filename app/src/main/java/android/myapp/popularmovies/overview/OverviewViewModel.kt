package android.myapp.popularmovies.overview

import android.myapp.popularmovies.network.Movie
import android.myapp.popularmovies.network.MovieApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/*
 OverviewViewModel will attach to OverviewFragment
 */
class OverviewViewModel : ViewModel(){

    //Internal Mutable Live Data that stores the statues

    private val _status = MutableLiveData<String>()

    //External immutable live data
    val status:LiveData<String>
     get() = _status

    // Internal Mutable Live Data that stores movie properties
    private val _movie = MutableLiveData<List<Movie>>()
    //External immutable Live data for movie properties
    val movie: LiveData<List<Movie>>
    get() =  _movie

    //
    private val _navigateMovie = MutableLiveData<Movie>()
    //
    val NavigateMovie : LiveData<Movie>
    get() = _navigateMovie

    // Coroutine Job variable
    private var viewModelJob = Job()

    //Coroutine Scope variable
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    /**
     * Call getMovieData on init so we can display data immediately
     */
    init {
        getMovieData()
    }

    //Get the movie data using Coroutine
    private fun getMovieData(){

        coroutineScope.launch {
            var getPropertiesDeferred = MovieApi.retrofitService.getMovies("21129ccd694a516f78fb06c6fae5f076")


            try {
                var listResult = getPropertiesDeferred?.await()
                if(listResult.MovieProperties.size > 0)
                    _movie.value = listResult.MovieProperties

                //_movieData.value = "Success: ${listResult.MovieProperties.size} properties retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"

            }

        }
    }

    //Cancel Coroutine job when View Model gets destroy
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    fun displayMovieDetails(movieDetails : Movie){
        _navigateMovie.value = movieDetails
    }
    fun displayMovieDetailsCompleted(){
        _navigateMovie.value = null
    }
}