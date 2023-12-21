package id.project.capstone.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertResult(saveRes: ResultUrineEntity)

    @Query("SELECT * FROM saveDataUrine ORDER BY time_stamp DESC")
    fun getAllResult(): LiveData<List<ResultUrineEntity>>

    @Query("DELETE FROM saveDataUrine WHERE id = :id")
    fun removeResult(id: Long)
}
