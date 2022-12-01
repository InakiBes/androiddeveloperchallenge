package com.religada.bemobile.utils

import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlin.math.round

val TAG_DEV = "_dev"

fun log(msg: String){
    Log.d(TAG_DEV, msg);
}

fun showSnackBar(view: View?, msg: String){
    view?.let {
        val snack = Snackbar.make(it, msg, Snackbar.LENGTH_LONG)
        snack.show()
    }
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}