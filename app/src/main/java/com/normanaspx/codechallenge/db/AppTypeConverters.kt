package com.normanaspx.codechallenge.db

import androidx.room.TypeConverter
import dagger.Provides

object AppTypeConverters {


    @TypeConverter
    @JvmStatic
    fun arrToString(values: Array<String>?): String?{
        return values?.joinToString ( "|" )
    }

    @TypeConverter
    @JvmStatic
    fun stringToArr(value: String?): Array<String>?{
        return value?.split("|")?.toTypedArray()
    }




}