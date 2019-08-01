package android.myapp.popularmovies.overview


import android.myapp.popularmovies.R
import android.myapp.popularmovies.databinding.FragmentOverviewBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 *
 */
class OverviewFragment : Fragment() {

   private lateinit var mBinding: FragmentOverviewBinding

    private val viewModel : OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_overview,container,false)

        //Allowing data binding to observe Live Data
        mBinding.setLifecycleOwner (this)

        //Binding access to the view model
        mBinding.viewModel = viewModel

        mBinding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener{
            viewModel.displayMovieDetails(it)

        })

//       mBinding.button.setOnClickListener {
//           findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToMovieDetailsFragment())
//       }
        mBinding.viewModel!!.NavigateMovie.observe(this, Observer {

            if(it!=null){
                findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToMovieDetailsFragment(it))
                mBinding.viewModel!!.displayMovieDetailsCompleted()
            }
        })

        return mBinding.root
    }


}
