package org.d3if3143.mobpro1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3143.mobpro1.model.Music

@Dao
interface MusicDao {

    @Insert
    suspend fun insert(music: Music)

    @Update
    suspend fun update(music: Music)

    @Query("SELECT * FROM music ORDER BY genre DESC")
    fun getMusic(): Flow<List<Music>>
}