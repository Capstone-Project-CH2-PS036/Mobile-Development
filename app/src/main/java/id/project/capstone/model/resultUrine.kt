package id.project.capstone.model

import androidx.room.*
import java.io.Serializable

@Dao
@Entity(tableName = "HistoriDataUrine")
data class resultUrine(

    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "urine_image")
    val image: String,

    @ColumnInfo(name = "result_data")
    val dataUrine: String

): Serializable
