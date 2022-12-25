package com.example.filmpopuler_taufan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmpopuler_taufan.data.remote.model.configuration.ConfigurationResponse
import com.example.filmpopuler_taufan.data.remote.model.movie.MoviesResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    /* metode insert merupakan metode yang menyisipkan
    parameter ke dalam table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieResponse: MoviesResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewConfiguration(configurationResponse: ConfigurationResponse)



    /*
        metode @query berfungsi untuk memasukan perintah sql ke dalam dao
        arti dari perintah select * from adalah
        menampilkan dari data tabel TBL_MOVIE atau TBL_CONFIGURATION
    */
    @Query("SELECT * FROM TBL_MOVIE")
    fun getAllMovies(): Flow<MoviesResponse>

    @Query("SELECT * FROM TBL_CONFIGURATION")
    fun getAllConfiguration(): Flow<ConfigurationResponse>

}