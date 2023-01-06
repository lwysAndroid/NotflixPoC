package com.hcl.notflixpoc.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkMovieResult(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val movies: List<NetworkMovie>,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
)
