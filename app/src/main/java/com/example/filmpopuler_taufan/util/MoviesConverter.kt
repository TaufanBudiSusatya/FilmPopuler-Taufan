package com.example.filmpopuler_taufan.util

import androidx.room.TypeConverter
import com.example.filmpopuler_taufan.data.remote.model.configuration.ConfigurationResponse
import com.example.filmpopuler_taufan.data.remote.model.configuration.Images
import com.example.filmpopuler_taufan.data.remote.model.movie.Movie
import com.example.filmpopuler_taufan.data.remote.model.movie.MoviesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MoviesConverter {

    private val gson = Gson()

    /*
    @TypeConverter merupakan anotasi dari RoomDb yang
    berfungsi untuk menyedikan Room dari tipe jenis data kustom
     */

    @TypeConverter
    fun fromMoviesResponseToString(moviesData: MoviesResponse): String {
        return gson.toJson(moviesData)
    }

    @TypeConverter
    fun fromStringToMoviesResponse(data: String): MoviesResponse {
        val typeToken = object : TypeToken<MoviesResponse>() {}.type
        return gson.fromJson(data, typeToken)
    }
    @TypeConverter
    fun fromConfigurationResponseToString(configurationData: ConfigurationResponse): String {
        return gson.toJson(configurationData)
    }

    @TypeConverter
    fun fromStringToConfigurationResponse(data: String): ConfigurationResponse {
        val typeToken = object : TypeToken<ConfigurationResponse>() {}.type
        return gson.fromJson(data, typeToken)
    }


    @TypeConverter
    fun fromMovieListToString(movieData: List<Movie>): String {
        return gson.toJson(movieData)
    }

    @TypeConverter
    fun fromStringToMovieList(data: String): List<Movie> {
        val typeToken = object : TypeToken<List<Movie>>() {}.type
        return gson.fromJson(data, typeToken)
    }


    @TypeConverter
    fun fromImageListToString(imageData: Images): String {
        return gson.toJson(imageData)
    }

    @TypeConverter
    fun fromStringToImageList(data: String): Images {
        val typeToken = object : TypeToken<Images>() {}.type
        return gson.fromJson(data, typeToken)
    }


    @TypeConverter
    fun fromChangeKeysListToString(imageData: List<String>): String {
        return gson.toJson(imageData)
    }

    @TypeConverter
    fun fromStringToChangeKeysList(data: String): List<String> {
        val typeToken = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, typeToken)
    }

}