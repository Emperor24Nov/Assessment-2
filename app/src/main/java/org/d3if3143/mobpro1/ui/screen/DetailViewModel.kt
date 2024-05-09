package org.d3if3143.mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3143.mobpro1.database.MusicDao
import org.d3if3143.mobpro1.model.Music

class DetailViewModel(private val dao: MusicDao) : ViewModel() {

    fun insert(judul: String, nama: String, tahun: String, genre: String) {
        val music = Music(
            judul = judul,
            nama = nama,
            tahun = tahun,
            genre = genre
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(music)
        }
    }

    suspend fun getMusic(id: Long): Music? {
        return dao.getMusicById(id)
    }

    fun update(id: Long, judul: String, nama: String, tahun: String, genre: String) {
        val music = Music(
            id = id,
            judul = judul,
            nama = nama,
            tahun = tahun,
            genre = genre
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(music)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}