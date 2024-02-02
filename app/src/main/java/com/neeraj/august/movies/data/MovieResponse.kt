package com.neeraj.august.moviedetails.data

import com.neeraj.august.movies.data.Movie


data class MovieResponse(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)