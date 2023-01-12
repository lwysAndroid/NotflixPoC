package com.hcl.notflixpoc.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkGenre (
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null
)