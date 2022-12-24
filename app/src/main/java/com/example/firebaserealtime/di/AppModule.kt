package com.example.firebaserealtime.di

import com.example.firebaserealtime.repository.RepositoryImp
import com.example.firebaserealtime.repository.TaskRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseDatabaseInstance(): DatabaseReference {
        return Firebase.database.getReference("Employees")
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        database: DatabaseReference
    ): TaskRepository {
        return RepositoryImp(database)
    }
}