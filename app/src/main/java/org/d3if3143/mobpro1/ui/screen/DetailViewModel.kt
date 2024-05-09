package org.d3if3143.mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3143.mobpro1.model.Music

class DetailViewModel : ViewModel() {

    fun getMusic(id: Long): Music {
        return Music(
            id,
            "p",
            "p",
            "p",
            "p"
        )
    }
}