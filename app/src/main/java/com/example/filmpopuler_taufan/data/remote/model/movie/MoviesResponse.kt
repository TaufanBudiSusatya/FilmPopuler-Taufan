package com.example.filmpopuler_taufan.data.remote.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmpopuler_taufan.data.remote.model.movie.MoviesResponse.Companion.TABLE_NAME
import com.google.gson.annotations.SerializedName

//perintah untuk memberikan nama table dengan anotosi Entity
@Entity(tableName = TABLE_NAME)
data class MoviesResponse(
    //@SerializedName annotation ialah
    // untuk menyamakan key pada model dan key pada JSON.
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    //mendeklrasikan variable TABLE_NAME yang berisikan
    //tbl_movie
    companion object {
        const val TABLE_NAME = "tbl_movie"
    }
}