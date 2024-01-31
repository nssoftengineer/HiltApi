package gaur.himanshu.august.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.august.moviedetails.data.MovieResponse
import gaur.himanshu.august.moviedetails.databinding.FragmentMovieBinding
import gaur.himanshu.august.movies.adapter.MovieAdapter
import gaur.himanshu.august.movies.utils.NsState
import gaur.himanshu.august.movies.viewmodel.NsViewModel
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    lateinit var binding: FragmentMovieBinding
    private val nsViewModel: NsViewModel by viewModels()
    var nsAdapter: MovieAdapter = MovieAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater)
        intRecyclerView()
        return binding.root
    }

    private fun intRecyclerView() {
        binding.movieRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter=nsAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        nsViewModel.getMovieList("marvel",10)
        lifecycleScope.launchWhenStarted {
            nsViewModel.nsState.collect {
                when(it){
                    is NsState.Loading->{

                    }
                    is NsState.Success<*> ->{
                     nsAdapter.setData(it.data as MovieResponse)
                     nsAdapter.notifyDataSetChanged()
                    }
                    is NsState.Failure->{

                    }
                }
            }
        }
    }

}