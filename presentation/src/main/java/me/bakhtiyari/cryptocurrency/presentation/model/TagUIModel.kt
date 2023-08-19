package me.bakhtiyari.cryptocurrency.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TagUIModel(
    val id: Long?,
    val tagType: Int?,
    val tagTypeName: String?,
    val name: String?,
    val symbol: String?,
    val rank: Long?,
    val logoUrl: String?,
    val isWatchlist: Boolean?
) : Parcelable