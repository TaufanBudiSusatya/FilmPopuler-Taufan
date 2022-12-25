package com.example.filmpopuler_taufan.data.remote

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.example.filmpopuler_taufan.util.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class NetworkController<RESULT, REQUEST> {
    fun asFlow() = flow {

        // Emit Loading State
        emit(State.loading())


        try {

            // Emit content database
            fetchFromLocal()?.let {
                emit(State.success(it.first()))
            }
            // mengambil currency dari remote
            val apiResponse = fetchFromRemote()

            // Parse body
            val remoteCurrencies = apiResponse.body()

            // cek validasi request
            if (apiResponse.isSuccessful && remoteCurrencies != null) {
                // menyimpan currencies
                emit(State.success(remoteCurrencies as RESULT))
                saveRemoteData(remoteCurrencies)
            } else {
                emit(State.error(apiResponse.message()))
            }
        } catch (e: Exception) {
            // menampilkan pesan jaringan eror
            emit(State.error("Network error! Can't get latest Data."))
            e.printStackTrace()
        }
    }

    /**
     * menyimpan retrieved dari remote ke  storage.
     */
    @WorkerThread
    abstract suspend fun saveRemoteData(response: REQUEST)

    /**
     * mengambil semua data dari persistence storage.
     */

    @MainThread
    abstract fun fetchFromLocal(): Flow<RESULT>?

    /**
     * mengambil [Response] dari the remote bagian akhir.
     */
    @MainThread
    protected abstract suspend fun  fetchFromRemote(): Response<REQUEST>
}