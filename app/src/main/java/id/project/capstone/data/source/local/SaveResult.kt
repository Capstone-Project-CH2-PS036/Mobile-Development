package id.project.capstone.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.project.capstone.data.source.local.resultUrine

@Dao
interface saveResult {

    @Insert
    fun addSaveResult(saveRes: resultUrine)

    @Query("SELECT * FROM saveDataUrine")
    fun getSaveResult(): LiveData<List<resultUrine>>

    @Query("DELETE FROM saveDataUrine WHERE saveDataUrine.id = :id")
    fun removeFavorite(id: Long): Long
}
