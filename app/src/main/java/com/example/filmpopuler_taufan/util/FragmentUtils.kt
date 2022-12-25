package com.example.filmpopuler_taufan.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
  menampilkan toast pada setiap fargment
 */
fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}
/**
 * Mengembalikan Warna dari sumber daya.
 * @param id ID Sumber Daya Warna
 */
fun Fragment.getColorRes(@ColorRes id: Int) = ContextCompat.getColor(requireContext(), id)

//menyediakan view model
inline fun <reified VM : ViewModel> AppCompatActivity.viewModelOf(factory: ViewModelProvider.Factory) =
    ViewModelProvider(this, factory)[VM::class.java]


fun Fragment.checkForInternet(context: Context): Boolean {

    // menghubungkan activity dengan service menager
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    /**
     jika versi android sama dengan M
     atau lebih besar kita perlu menggunakan the
     NetworkCapabilities untuk memeriksa jenis apa
     jaringan memiliki koneksi internet
     */
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        // Mengembalikan objek Jaringan yang sesuai dengan
        // jaringan data default yang sedang aktif.
        val network = connectivityManager.activeNetwork ?: return false

// Representasi kemampuan jaringan aktif.
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            // Menunjukkan jaringan ini menggunakan transportasi Wi-Fi,
            // atau WiFi memiliki konektivitas jaringan
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true


            // else return false
            else -> false
        }
    } else {
        // jika versi android di bawah M
        @Suppress("DEPRECATION") val networkInfo =
            connectivityManager.activeNetworkInfo ?: return false
        @Suppress("DEPRECATION")
        return networkInfo.isConnected
    }


}
