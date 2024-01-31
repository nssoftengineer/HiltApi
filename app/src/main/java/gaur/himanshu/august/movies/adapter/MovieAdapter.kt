package gaur.himanshu.august.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gaur.himanshu.august.moviedetails.data.Movie
import gaur.himanshu.august.moviedetails.data.MovieResponse

import gaur.himanshu.august.moviedetails.databinding.MovieRowBinding


class MovieAdapter(var movieList: List<Movie>) : RecyclerView.Adapter<MovieAdapter.NsViewHolder>() {
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
    }

    fun setData(movieResponse: MovieResponse) {
        movieList = movieResponse.Search
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}