package com.normanaspx.codechallenge.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


/**
Created by Norman  7/7/2021
 **/

@Parcelize
@Entity(tableName="character")
data class Character (
        @PrimaryKey
        @SerializedName("char_id")
        val char_id: Int,
        var name: String,
        val occupation: Array<String>,
        val img: String?,
        val status: String,
        val nickname: String,
        var isLiked: Boolean = false
) : Parcelable