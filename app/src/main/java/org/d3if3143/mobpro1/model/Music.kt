package org.d3if3143.mobpro1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
data class Music(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val judul: String,
    val nama: String,
    val tahun: String,
    val genre: String
)
