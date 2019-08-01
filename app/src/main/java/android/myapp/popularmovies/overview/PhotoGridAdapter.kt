package android.myapp.popularmovies.overview

import android.myapp.popularmovies.databinding.GridViewItemBinding
import android.myapp.popularmovies.network.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PhotoGridAdapter(val onClickListener: OnClickListener) : ListAdapter<Movie, PhotoGridAdapter.MovieViewHolder>(DiffCallback)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener{
            onClickListener.clickListener(movie)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }
    class MovieViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
    class OnClickListener(val clickListener: (movie: Movie) -> Unit) {
        fun onClick(movie: Movie) = clickListener(movie)
    }
}