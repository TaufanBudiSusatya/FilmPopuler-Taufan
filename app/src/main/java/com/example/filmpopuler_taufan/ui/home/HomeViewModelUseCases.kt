package com.example.filmpopuler_taufan.ui.home

import androidx.paging.PagingData
import com.example.filmpopuler_taufan.data.remote.model.configuration.ConfigurationResponse
import com.example.filmpopuler_taufan.data.remote.model.movie.Movie
import com.example.filmpopuler_taufan.util.State
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow

/*
view model use case untuk mengurangi
beban kelas ViewModel dan mengurangi duplikasi dan kerumitan kode
 */
@ActivityRetainedScoped
interface HomeViewModelUseCases {


    val movieList: Flow<PagingData<Movie>>
    val configurationLiveData: Flow<State<ConfigurationResponse>>

    fun getConfigurationData()
}