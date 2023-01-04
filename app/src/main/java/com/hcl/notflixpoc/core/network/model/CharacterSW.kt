package com.hcl.notflixpoc.core.network.model

import com.google.gson.annotations.SerializedName

data class CharacterSW(
    @SerializedName("name")
    val name:String,
    @SerializedName("gender")
    val gender:String,
)
