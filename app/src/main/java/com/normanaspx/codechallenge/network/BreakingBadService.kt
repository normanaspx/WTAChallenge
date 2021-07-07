package com.normanaspx.codechallenge.network

import com.normanaspx.codechallenge.model.Character
import retrofit2.http.GET




interface BreakingBadService {

    @GET("characters")
    suspend fun getCharacters(): List<Character>


}