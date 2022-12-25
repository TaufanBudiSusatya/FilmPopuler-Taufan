package com.example.filmpopuler_taufan.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.example.filmpopuler_taufan.R
import com.example.filmpopuler_taufan.data.remote.model.movie.Movie
import com.example.filmpopuler_taufan.databinding.HomeFragmentBinding
import com.example.filmpopuler_taufan.ui.base.BaseFragment
import com.example.filmpopuler_taufan.ui.home.adapter.MoviesAdapter
import com.example.filmpopuler_taufan.util.ConfigurationStore
import com.example.filmpopuler_taufan.util.State
import com.example.filmpopuler_taufan.util.checkForInternet
import com.example.filmpopuler_taufan.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding>(),
    MoviesAdapter.Interaction {

    //mendeklrasikan variable yang dibutuhkan
    override val mViewModel: HomeViewModel by viewModels()

    private val adapter = MoviesAdapter(this)


    override fun getViewBinding(view: View) = HomeFragmentBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovies.adapter = adapter
        if (checkForInternet(requireContext())) {
            if (adapter.itemCount == 0) {
                getConfigurationData()
                getALlMovies()
            } else
                hideShimmerEffect()


        } else
            //menampilkan pesan toast
            showToast(getString(R.string.msg_no_internet_connection))


    }
    //membuat fitur jika di gambar di klik maka akan ke detial fragment
    override fun onItemSelected(position: Int, item: Movie, extra: FragmentNavigator.Extras) {

        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item)
        navigate(action, extra)
    }



    private fun getALlMovies() {
    //memulai tampilan dengan komponen viewmode dari lifecyclescope
        lifecycleScope.launchWhenStarted {
            mViewModel.movieList.collectLatest { it ->
                if (it != null) {
                    hideShimmerEffect()
                    adapter.submitData(it)
                }

            }
        }


    }

    private fun getConfigurationData() {

        lifecycleScope.launchWhenStarted {
            //viewmodel dengan konfigurasi live data
            mViewModel.configurationLiveData.collect {
                when (it) {
                    is State.Loading -> {}
                    is State.Success -> {
                        it.data?.let {
                            ConfigurationStore.setConfigurationData(it)
                        }

                    }
                    //jika keadaan eror maka akan menampilkan pesan dari it
                    is State.Error -> {
                        showToast(it.message)
                    }
                }
            }
        }
        mViewModel.getConfigurationData()

    }

    private fun hideShimmerEffect() {
        binding.apply {
            shimmerView.stopShimmer()
            shimmerView.visibility = View.GONE
            rvMovies.visibility = View.VISIBLE
            gEmpty.visibility = View.GONE
        }

    }

    private fun navigate(destination: NavDirections, extraInfo: FragmentNavigator.Extras) =
        with(findNavController()) {
            currentDestination?.getAction(destination.actionId)
                ?.let {
                    navigate(destination, extraInfo)
                }
        }


    companion object {
        fun newInstance() = HomeFragment()
        private const val TAG = "HomeFragment"
    }


}