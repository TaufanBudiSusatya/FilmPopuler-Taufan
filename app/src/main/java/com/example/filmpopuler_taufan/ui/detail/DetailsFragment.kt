package com.example.filmpopuler_taufan.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.filmpopuler_taufan.databinding.FragmentDetailsBinding
import com.example.filmpopuler_taufan.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsViewModel, FragmentDetailsBinding>() {

    // mendeklarasikan variabel
    override val mViewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    //meminta request dari FragmentDetailBinding
    override fun getViewBinding(view: View) = FragmentDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovieDetails()
        //ketika di klik pada halaman utaama maka akan masuk ke dalam tampilan kedua
        //atau detail fragment
        binding.apply {
            ivBack.setOnClickListener { findNavController().navigateUp() }
        }
    }



    private fun getMovieDetails() {
        args.let {
            binding.movie = it.movieData

            val transition = TransitionInflater.from(requireContext())
                .inflateTransition(android.R.transition.move)
            sharedElementEnterTransition = transition
            sharedElementReturnTransition = transition

        }
    }


    //endregion

}