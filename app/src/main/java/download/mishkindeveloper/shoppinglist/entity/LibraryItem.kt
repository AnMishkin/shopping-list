package download.mishkindeveloper.shoppinglist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//библиотека для пользователя,тех слов или предметов для покупки которіе он уже вводил
@Entity (tableName = "library")
data class LibraryItem(
    @PrimaryKey (autoGenerate = true)
    val id:Int?,

    @ColumnInfo(name = "name")
    val name:String
)
