package com.hcl.notflixpoc.core.network.model

import com.google.gson.annotations.SerializedName

data class PeopleSWContainer(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("results")
    val results: List<PeopleSW>
)
