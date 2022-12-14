package download.mishkindeveloper.shoppinglist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "shopping_list_names")
data class ShoppingList (
    @PrimaryKey(autoGenerate = true)
    val id:Int?,

    //название продукта
    @ColumnInfo(name = "name")
    val name:String?,

    //колличество для покупки
//    @ColumnInfo(name = "amount")
//    val amount:Int?,

    //время когда было добавлено
    @ColumnInfo(name = "time")
    val time:String?,

    //счетчик для всех продуктов
    @ColumnInfo(name = "allItemCounter")
    val allItemCounter:Int?,

    //счетчик для продуктов,какие уже купили
    @ColumnInfo(name = "checkedItemCounter")
    val checkedItemCounter:Int?,

    //айди продуктов
    @ColumnInfo(name = "itemsIds")
    val itemsIds:String?,

        ) : Serializable