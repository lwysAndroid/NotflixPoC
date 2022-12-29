package com.hcl.notflixpoc.core.network

import com.hcl.notflixpoc.core.network.model.PeopleSWContainer

interface NotflixPoCNetworkDataSource {
    suspend fun getPeopleSW(): PeopleSWContainer
}