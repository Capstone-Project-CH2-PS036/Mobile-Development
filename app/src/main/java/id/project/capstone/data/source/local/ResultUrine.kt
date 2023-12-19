package id.project.capstone.data.source.local

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "saveDataUrine")
data class resultUrine(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "urine_image")
    val image: String,

    @ColumnInfo(name = "result_data")
    val dataUrine: String

): Serializable
