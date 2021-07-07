package com.normanaspx.codechallenge.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.normanaspx.codechallenge.model.Character
import com.normanaspx.codechallenge.repository.CharacterRepository
import kotlinx.coroutines.launch


/**
Created by Norman on 7/7/2021
 **/
class CharacterViewModel @ViewModelInject constructor (private val characterRepository: CharacterRepository) :ViewModel(){


    val characters =characterRepository.characters


    fun refreshData(){
        viewModelScope.launch {
            characterRepository.refreshCharacters()
        }
    }

    fun update(character:Character){
        viewModelScope.launch {
            characterRepository.updateCharacter(character)
        }
    }


}