package com.neeraj.august.movies.repo

import com.neeraj.august.moviedetails.data.MovieResponse
import com.neeraj.august.moviedetails.data.moviedetails.MovieDetails
import com.neeraj.august.movies.network.NsServices
import com.neeraj.august.movies.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class SearchMovies @Inject constructor(var nsServices: NsServices) {

    suspend fun getMovieList(name: String, pageSize: Int): Flow<MovieResponse> {
        return flow {
            emit(nsServices.getAllMovies(name, pageSize, Constants.API_KEY))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieDetail(imdb: String): Flow<MovieDetails> {
        return flow {
            emit(nsServices.getMovieDetails(imdb, Constants.API_KEY))
        }.flowOn(Dispatchers.IO)
    }
}