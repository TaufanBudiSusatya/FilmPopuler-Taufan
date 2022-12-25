package com.example.filmpopuler_taufan.data.remote.api

import com.example.filmpopuler_taufan.data.remote.model.configuration.ConfigurationResponse
import com.example.filmpopuler_taufan.data.remote.model.movie.MoviesResponse
import com.example.filmpopuler_taufan.util.CONFIGURATION_URL
import com.example.filmpopuler_taufan.util.TRENDING_MOVIES_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    /*
    mengambil variabel TRENDING_MOVIES_URL
    lalu dengan perintah @Query memberikan respom ke file MoviesResponse
     */
    @GET(TRENDING_MOVIES_URL)
    suspend fun getTrendingMovies(
        @Query("page") page:Int?=1
    ): Response<MoviesResponse>



    /*
     mengambil variabel CONFIGURATION_URL
     lalu  memberikan respom ke file ConfigurationResponse
         */
    @GET(CONFIGURATION_URL)
    suspend fun getConfiguration(
    ): Response<ConfigurationResponse>


}