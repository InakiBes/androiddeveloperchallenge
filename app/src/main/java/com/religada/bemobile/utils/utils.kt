package com.religada.bemobile.utils

import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar

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