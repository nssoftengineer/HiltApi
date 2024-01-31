package gaur.himanshu.august.movies.network

import gaur.himanshu.august.moviedetails.data.MovieResponse
import gaur.himanshu.august.moviedetails.data.moviedetails.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface NsServices {
    @GET("/")
    suspend fun getAllMovies(
        @Query("s") s: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ): MovieResponse


    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apiKey") apiKey: String
    ): MovieDetails
}