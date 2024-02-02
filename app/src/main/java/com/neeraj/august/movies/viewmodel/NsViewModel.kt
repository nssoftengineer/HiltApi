package com.neeraj.august.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.neeraj.august.movies.repo.SearchMovies
import com.neeraj.august.movies.utils.NsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NsViewModel @Inject constructor(var searchMovies: SearchMovies) : ViewModel() {

    val nsState: MutableStateFlow<NsState> = MutableStateFlow(NsState.Loading)

    fun getMovieList(name: String, pageSize: Int): Flow<NsState> {
        nsState.value = NsState.Loading
        viewModelScope.launch {
            searchMovies.getMovieList(name, pageSize).catch {
                nsState.value = NsState.Failure(it)
            }.collect {
                nsState.value = NsState.Success(it)
            }
        }
        return nsState
    }

    fun getMovieDetail(id: String): Flow<NsState> {
        nsState.value = NsState.Loading
        viewModelScope.launch {
            searchMovies.getMovieDetail(id).catch {
                nsState.value = NsState.Failure(it)
            }.collect {
                nsState.value = NsState.Success(it)
            }
        }
        return nsState
    }
}