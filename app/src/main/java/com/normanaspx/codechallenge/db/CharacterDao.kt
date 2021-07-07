package com.normanaspx.codechallenge.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.normanaspx.codechallenge.model.Character

@Dao
interface CharacterDao {

    @Query("select * from character order by isLiked desc")
    fun getAll(): LiveData<List<Character>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(characters: List<Character>)

    @Update()
    suspend fun updateCharacter(character: Character)


}