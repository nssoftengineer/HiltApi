package gaur.himanshu.august.moviedetails.data

import gaur.himanshu.august.movies.data.Movie


data class MovieResponse(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)