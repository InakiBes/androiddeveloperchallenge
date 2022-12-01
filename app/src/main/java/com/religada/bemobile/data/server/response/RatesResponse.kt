package com.religada.bemobile.data.server.response

import com.google.gson.annotations.SerializedName

class RateResponse(
    @SerializedName("form") // TODO notify the person responsible for the REST API that the field is misspelled
    val from: String = "",
    val to: String = "",
    val rate: Double = 0.0,
)
