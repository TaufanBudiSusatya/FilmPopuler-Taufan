package com.example.filmpopuler_taufan.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.filmpopuler_taufan.data.local.dao.MovieDao
import com.example.filmpopuler_taufan.data.remote.NetworkController
import com.example.filmpopuler_taufan.data.remote.NetworkDataSourceImp
import com.example.filmpopuler_taufan.data.remote.model.configuration.ConfigurationResponse
import com.example.filmpopuler_taufan.data.remote.model.movie.MoviesResponse
import com.example.filmpopuler_taufan.ui.home.TrendingPagingSource
import com.example.filmpopuler_taufan.util.State
import dagger.hilt.android.scopes.ActivityRetainedScoped

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject


@ActivityRetainedScoped
class MoviesRepository @Inject constructor(
    private val moviesService: NetworkDataSourceImp,
    private val dao: MovieDao
)
{
    /*
    mendapatkan seluruh daata movie dengan
    objek movieresponse
    dan simpan dalam movieresponse
     */
    fun getAllMoviesData(): Flow<State<MoviesResponse>> {
        return object: NetworkController<MoviesResponse, MoviesResponse>(){
            override suspend fun saveRemoteData(response: MoviesResponse) =Unit

            override fun fetchFromLocal(): Flow<MoviesResponse>? =null

            override suspend fun fetchFromRemote(): Response<MoviesResponse> = moviesService.getAllMoviesData(1)

        }.asFlow().flowOn(Dispatchers.IO)
    }

    /*
    mendapatkan konfigurasi data
     */
    fun getConfigurationData(): Flow<State<ConfigurationResponse>> {
        return   object: NetworkController<ConfigurationResponse, ConfigurationResponse>(){
            override suspend fun saveRemoteData(response: ConfigurationResponse) =dao.insertNewConfiguration(response)

            override fun fetchFromLocal(): Flow<ConfigurationResponse> =dao.getAllConfiguration()

            override suspend fun fetchFromRemote(): Response<ConfigurationResponse> = moviesService.getConfigurationData()
        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun getTrends(
        page:Int
    ) = Pager(
        PagingConfig(
            pageSize = 20,
            maxSize = 100
        )
    ) {
        TrendingPagingSource(moviesService)
    }

}


