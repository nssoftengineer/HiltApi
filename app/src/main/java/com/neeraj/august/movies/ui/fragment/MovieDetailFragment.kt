package com.neeraj.august.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.neeraj.august.movies.data.Movie
import com.neeraj.august.moviedetails.databinding.FragmentDetailsBinding
import com.neeraj.august.movies.viewmodel.NsViewModel
import androidx.databinding.library.baseAdapters.BR;
@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    val nsViewModel: NsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        binding.setVariable(BR.movie, setData)
        return binding.root
    }

    companion object{
        lateinit var setData: Movie
    }
}