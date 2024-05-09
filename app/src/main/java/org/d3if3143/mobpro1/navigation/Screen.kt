package org.d3if3143.mobpro1.navigation

import org.d3if3143.mobpro1.ui.screen.KEY_ID_MUSIC

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object FormMusic: Screen("musicScreen")
    data object FormUbah: Screen("musicScreen/{$KEY_ID_MUSIC}") {
        fun withId(id: Long) = "musicScreen/$id"
    }
}