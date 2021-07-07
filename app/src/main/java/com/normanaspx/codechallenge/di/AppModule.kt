package com.normanaspx.codechallenge.di

import android.content.Context
import androidx.room.Room
import com.normanaspx.codechallenge.BreakingBadApplication
import com.normanaspx.codechallenge.db.BBDatabase
import com.normanaspx.codechallenge.db.CharacterDao
import com.normanaspx.codechallenge.network.BreakingBadService
import com.normanaspx.codechallenge.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.annotation.Signed
import javax.inject.Singleton

/**
 * This component is used for general purposes
 * it is placed in the application component since
 * it can be injected on any other modules where
 * it is needed
 *
 * @author Norman Vicente
 *
 * */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BreakingBadApplication.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideBreakingBadService(retrofit: Retrofit): BreakingBadService =
        retrofit.create(BreakingBadService::class.java)

    @Provides
    fun provideDao(db: BBDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideRoomInstace(@ApplicationContext ctx: Context) = Room.databaseBuilder(ctx, BBDatabase::class.java,"bb").build()

    @Provides
    fun providesCharacterRepository(characterDao: CharacterDao, service: BreakingBadService): CharacterRepository
    = CharacterRepository(characterDao, service)


}