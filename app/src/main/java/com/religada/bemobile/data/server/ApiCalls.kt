package com.religada.bemobile.data.server

import com.religada.bemobile.data.server.response.RateResponse
import com.religada.bemobile.data.server.response.TransactionResponse

import retrofit2.http.GET

interface ApiCalls {
    @GET("rates.json")
    suspend fun getRates(
       ): List<RateResponse>

    @GET("transactions.json")
    suspend fun getTransactions(
    ): List<TransactionResponse>
}

