package id.project.capstone.data.source.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "saveDataUrine")
@Parcelize
data class ResultUrineEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "urine_image")
    val image: String?,

    @ColumnInfo(name = "color")
    val color: String?,

    @ColumnInfo(name = "result_data")
    val dataUrine: String?,

    @ColumnInfo(name = "time_stamp")
    val timestamp: Long = System.currentTimeMillis()
): Parcelable
