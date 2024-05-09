package org.d3if3143.mobpro1.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object FormMusic: Screen("musicScreen")
}