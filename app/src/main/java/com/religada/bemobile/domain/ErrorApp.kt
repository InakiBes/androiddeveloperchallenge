package com.religada.bemobile.domain

sealed interface ErrorApp {
    class Server(val code: Int) : ErrorApp
    object Connectivity : ErrorApp
    class Unknown(val message: String) : ErrorApp
}