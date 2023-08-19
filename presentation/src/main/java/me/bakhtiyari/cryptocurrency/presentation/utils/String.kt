package me.bakhtiyari.cryptocurrency.presentation.utils

import me.bakhtiyari.cryptocurrency.presentation.BuildConfig


fun String.addMediaBaseUrl(): String = BuildConfig.BASE_URL + this