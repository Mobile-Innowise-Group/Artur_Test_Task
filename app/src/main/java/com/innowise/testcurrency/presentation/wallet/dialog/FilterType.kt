package com.innowise.testcurrency.presentation.wallet.dialog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class FilterType : Parcelable {

    object Equivalent : FilterType()
    data class Tag(val tags: Set<String>) : FilterType()
}