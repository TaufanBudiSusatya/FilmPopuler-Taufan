package com.example.filmpopuler_taufan.util

/**
 * mengatur Ui & data
 */
sealed class State<T> {
    class Loading<T> : State<T>()

    data class Success<T>(val data: T) : State<T>()

    data class Error<T>(val message: String) : State<T>()

    companion object {

        /**
         * Returns [State.Loading] instance.
         */
        fun <T> loading() = Loading<T>()

        /**
         * Returns [State.Success] instance.
         */
        fun <T> success(data: T) =
            Success(data)

        /**
         * Returns [State.Error] instance.
         */
        fun <T> error(message: String) =
            Error<T>(message)
    }

}