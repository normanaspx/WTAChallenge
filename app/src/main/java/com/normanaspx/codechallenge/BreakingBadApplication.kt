package com.normanaspx.codechallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
Created by Norman on 7/7/2021
 **/

@HiltAndroidApp
class BreakingBadApplication: Application() {

    companion object{
        const val API_BASE_URL = "https://www.breakingbadapi.com/api/"
    }

}