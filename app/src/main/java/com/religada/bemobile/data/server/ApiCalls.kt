package com.religada.bemobile.data.server

import com.religada.bemobile.data.server.response.RatesResponse
import com.religada.bemobile.data.server.response.TransactionsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiCalls {
    @GET("rates.json")
    suspend fun getRates(
       ): RatesResponse

    @GET("transactions.json")
    suspend fun getTransactions(
    ): TransactionsResponse
}

