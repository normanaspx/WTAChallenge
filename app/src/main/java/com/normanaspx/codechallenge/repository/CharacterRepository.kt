package com.normanaspx.codechallenge.repository

import androidx.lifecycle.LiveData
import com.normanaspx.codechallenge.db.CharacterDao
import com.normanaspx.codechallenge.model.Character
import com.normanaspx.codechallenge.network.BreakingBadService
import javax.inject.Inject


/**
 * Class to handle API calls to get Characters
 *
 * @author Norman Vicente
 *
 * */
class CharacterRepository @Inject constructor(private val characterDao: CharacterDao,private val service:BreakingBadService) {

    /**
     * This method gets the characters from the database.
     * @return LiveData to be handle on the CharacterAdapter
     * */
    val characters: LiveData<List<Character>> = characterDao.getAll()

    /**
     * This method gets the characters from the service
     * */
    suspend fun  refreshCharacters(){
        val characters = service.getCharacters()
        characterDao.insertAll(characters)
    }

    suspend fun updateCharacter(character: Character){
        characterDao.updateCharacter(character)
    }


}