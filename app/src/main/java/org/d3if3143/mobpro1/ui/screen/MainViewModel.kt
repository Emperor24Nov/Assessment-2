package org.d3if3143.mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3143.mobpro1.model.Music

class MainViewModel : ViewModel() {

    val data = getDataDummy()

    private fun getDataDummy(): List<Music> {
        val data = mutableListOf<Music>()
        for (i in 29 downTo 20) {
            data.add(
                Music(
                    i.toLong(),
                    "Music ke $i",
                    "Aruno",
                    "20$i-02-20",
                    "Pop",
                )
            )
        }
        return data
    }
}