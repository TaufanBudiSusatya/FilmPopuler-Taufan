package com.example.filmpopuler_taufan.data.remote.model.configuration

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//@Parcelize mengharuskan agar semua properti
// yang diserialisasi dideklarasikan dalam konstruktor utama.
@Parcelize
data class Images(
    //@SerializedName annotation ialah
    // untuk menyamakan key pada model dan key pada JSON.
    @SerializedName("backdrop_sizes")
    val backdropSizes: List<String>,
    @SerializedName("base_url")
    val baseUrl: String,
    @SerializedName("logo_sizes")
    val logoSizes: List<String>,
    @SerializedName("poster_sizes")
    val posterSizes: List<String>,
    @SerializedName("profile_sizes")
    val profileSizes: List<String>,
    @SerializedName("secure_base_url")
    val secureBaseUrl: String,
    @SerializedName("still_sizes")
    val stillSizes: List<String>
): Parcelable