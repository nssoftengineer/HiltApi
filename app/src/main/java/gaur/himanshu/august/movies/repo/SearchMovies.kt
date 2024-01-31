package gaur.himanshu.august.movies.repo

import gaur.himanshu.august.moviedetails.data.MovieResponse
import gaur.himanshu.august.moviedetails.data.moviedetails.MovieDetails
import gaur.himanshu.august.movies.network.NsServices
import gaur.himanshu.august.movies.utils.Constants
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