package org.d3if3143.mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3143.mobpro1.database.MusicDao
import org.d3if3143.mobpro1.model.Music

class MainViewModel(dao: MusicDao) : ViewModel() {

    val data: StateFlow<List<Music>> = dao.getMusic().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}