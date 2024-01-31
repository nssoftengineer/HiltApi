package gaur.himanshu.august.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.august.moviedetails.databinding.FragmentDetailsBinding
import gaur.himanshu.august.movies.viewmodel.NsViewModel
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
        return binding.root
    }
}