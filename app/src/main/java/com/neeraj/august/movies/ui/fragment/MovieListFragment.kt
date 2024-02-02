package com.neeraj.august.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import com.neeraj.august.moviedetails.data.MovieResponse
import com.neeraj.august.moviedetails.databinding.FragmentMovieBinding
import com.neeraj.august.movies.adapter.MovieAdapter
import com.neeraj.august.movies.utils.NsState
import com.neeraj.august.movies.viewmodel.NsViewModel
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    lateinit var binding: FragmentMovieBinding
    private val nsViewModel: NsViewModel by viewModels()
    var nsAdapter: MovieAdapter = MovieAdapter(emptyList(),this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater,container,false)
        intRecyclerView()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieSearch.setOnQueryTextListener(object : OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (newText.length > 2)
                        nsViewModel.getMovieList(newText, 10)
                }
                return false
            }
        })

    }

    private fun intRecyclerView() {
        binding.movieRecycler.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter=nsAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        nsViewModel.getMovieList("",10)
        lifecycleScope.launchWhenStarted {
            nsViewModel.nsState.collect {
                when(it){
                    is NsState.Loading->{

                    }
                    is NsState.Success<*> ->{ var movies = it.data as MovieResponse
                     if(movies.Response.equals("true",true))
                     nsAdapter.setData(movies)
                     else
                         Toast.makeText(requireContext(), "Record not found.", Toast.LENGTH_SHORT).show()
                     nsAdapter.notifyDataSetChanged()
                    }
                    is NsState.Failure->{

                    }
                }
            }
        }
    }


}