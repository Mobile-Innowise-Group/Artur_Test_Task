package com.innowise.testcurrency.presentation.filter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class PickedFilter : Parcelable {

    object Ascending : PickedFilter()
    object Descending : PickedFilter()
    data class Tags(val tags: Set<String>) : PickedFilter()
}
