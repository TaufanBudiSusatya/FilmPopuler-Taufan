package com.example.filmpopuler_taufan.di

import android.content.Context
import androidx.room.Room
import com.example.filmpopuler_taufan.data.local.MovieDataBase
import com.example.filmpopuler_taufan.data.local.dao.MovieDao
import com.example.filmpopuler_taufan.util.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//anatosi  @InstallIn berfungsi untuk instalasi sebuah class
@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Provides
    @Singleton
    //membuat roomdatabase pada class MovideDataBase
    //dengan variabel DB_NAME
    fun provideRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        MovieDataBase::class.java,
        DB_NAME
    ).build()


    @Provides
    @Singleton
    fun provideDao(roomDatabase: MovieDataBase): MovieDao {
        return roomDatabase.getDao()
    }
}