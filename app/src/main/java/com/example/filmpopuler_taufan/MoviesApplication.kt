package com.example.filmpopuler_taufan

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Komponen Hilt yang dihasilkan ini dipasang
// ke siklus proses objek Application dan menyediakan dependensi ke objek tersebut
@HiltAndroidApp
class MoviesApplication: Application()