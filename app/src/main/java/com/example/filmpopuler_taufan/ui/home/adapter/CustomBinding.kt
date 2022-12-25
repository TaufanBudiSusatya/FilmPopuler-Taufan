package com.example.filmpopuler_taufan.ui.home.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.filmpopuler_taufan.R
import com.example.filmpopuler_taufan.util.ConfigurationStore

/*
membuat data bingung untuk membuat view model
 */
@BindingAdapter("file_path")
fun ImageView.loadImageUrl(file_path: String) {

    /*
    mendklrasikan variabel baseUrl
    dan completeUrl
     */
    val baseUrl = ConfigurationStore.getConfigurationData().images.secureBaseUrl
    val completeUrl = "${baseUrl}original$file_path"

    /*memuat dari variable completeUrl
    dan jika eror akan menampilkan gambar dari drwable
     */
    load(completeUrl)
    {
        error(R.drawable.ic_place_holder)
        transformations(RoundedCornersTransformation())
        crossfade(600)
        scale(coil.size.Scale.FILL)
    }

}