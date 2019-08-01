package android.myapp.popularmovies.detail


import android.myapp.popularmovies.R
import android.myapp.popularmovies.databinding.FragmentMovieDetailsBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MovieDetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentMovieDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_details,container,false)
        val application = requireNotNull(activity).application

        val movie = MovieDetailsFragmentArgs.fromBundle(arguments!!).selectedMovie

        val viewModelFactory = MovieDetailViewModelFactory(movie, application)

        mBinding.detailViewModel = ViewModelProviders.of(
            this, viewModelFactory).get(MovieDetailsViewModel::class.java)

        return mBinding.root
    }


}
