package com.deny.myanimes.navigation

const val HOME_NAV_TAG = "home_view"
const val DETAILS_NAV_TAG = "animes_details"
const val DETAILS_ARGUMENT_KEY1 = "title"
const val DETAILS_ARGUMENT_KEY2 = "synopsis"
const val DETAILS_ARGUMENT_KEY3 = "youtubeId"
const val DETAILS_ARGUMENT_KEY4 = "image"

sealed class Screens(val route: String) {
    object home: Screens(route = HOME_NAV_TAG)
    object details: Screens(route = DETAILS_NAV_TAG+"/{${DETAILS_ARGUMENT_KEY1}}/{${DETAILS_ARGUMENT_KEY2}}/{${DETAILS_ARGUMENT_KEY3}}/{${DETAILS_ARGUMENT_KEY4}}")
}