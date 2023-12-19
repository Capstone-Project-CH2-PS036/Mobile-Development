package id.project.capstone.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertResult(saveRes: ResultUrineEntity)

    @Query("SELECT * FROM saveDataUrine")
    fun getAllResult(): LiveData<List<ResultUrineEntity>>

    @Delete
    fun removeResult(id: ResultUrineEntity)
}
