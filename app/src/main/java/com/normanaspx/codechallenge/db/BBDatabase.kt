package com.normanaspx.codechallenge.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.normanaspx.codechallenge.model.Character


/**
    Created by Norman  7/7/2021
 **/

@Database(
    entities = [Character::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(AppTypeConverters::class)
abstract class BBDatabase : RoomDatabase(){
    abstract fun characterDao(): CharacterDao

}