package com.religada.bemobile.widget.customdialog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CustomModalData(
    val title: String? = null,
    val info: String,
    val isModalInfo: Boolean = true,
    val accept: (() -> Unit)? = null,
    val cancel: (() -> Unit)? = null,
    val textAccept: String?,
    val textCancel: String? = null,
) : Parcelable
