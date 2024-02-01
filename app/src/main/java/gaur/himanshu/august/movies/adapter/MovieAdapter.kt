package gaur.himanshu.august.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gaur.himanshu.august.moviedetails.R
import gaur.himanshu.august.movies.data.Movie
import gaur.himanshu.august.moviedetails.data.MovieResponse

import gaur.himanshu.august.moviedetails.databinding.MovieRowBinding
import gaur.himanshu.august.movies.ui.fragment.MovieDetailFragment


class MovieAdapter(var movieList: List<Movie>, var requrieFragment: Fragment) : RecyclerView.Adapter<MovieAdapter.NsViewHolder>() {
    lateinit var binding: MovieRowBinding

    class NsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NsViewHolder {
        binding = MovieRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NsViewHolder, position: Int) {
        binding.movieTitle.text = movieList.get(position).Title
        binding.movieDec.text = movieList.get(position).Year
        Glide.with(binding.movieImage).load(movieList.get(position).Poster).into(binding.movieImage)
        binding.root.setOnClickListener {
            MovieDetailFragment.setData = movieList.get(position)
            Navigation.findNavController(binding.root).navigate(R.id.movieDetailFragment)
        }
    }



    fun setData(movieResponse: MovieResponse) {
        movieList = movieResponse.Search
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}