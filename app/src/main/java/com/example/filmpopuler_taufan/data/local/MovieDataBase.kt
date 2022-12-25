package com.example.filmpopuler_taufan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.filmpopuler_taufan.data.local.dao.MovieDao
import com.example.filmpopuler_taufan.data.remote.model.configuration.ConfigurationResponse
import com.example.filmpopuler_taufan.data.remote.model.movie.MoviesResponse
import com.example.filmpopuler_taufan.util.MoviesConverter

/*
perintah @Database berfungsi menentukan class untuk menyimpan database
>>MoviesResponse dan ConfigurationResponse adalah class yang digunakan
 */
@Database(entities = [MoviesResponse::class, ConfigurationResponse::class], version = 1)


/*
tujuan dari @TypeConverters adalah menyimpan jenis data kustom
dalam suatu kolom database lalu menambahkan class MoviesConverter
dengan tujuan sebagai class pengonversi
 */
@TypeConverters(MoviesConverter::class)
abstract class MovieDataBase: RoomDatabase() {
    abstract  fun getDao(): MovieDao
}