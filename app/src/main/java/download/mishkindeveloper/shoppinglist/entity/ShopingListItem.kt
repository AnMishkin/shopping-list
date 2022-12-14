package download.mishkindeveloper.shoppinglist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "shop_list_item")
data class ShopingListItem (
@PrimaryKey(autoGenerate = true)
val id:Int?,

@ColumnInfo (name = "name")
val name:String,

@ColumnInfo (name = "itemInfo")
val itemInfo:String,

@ColumnInfo (name = "ItemChecked")
val itemChecked:Int = 0,

@ColumnInfo (name = "listId")
val listId:Int ,

//подсказка
@ColumnInfo (name = "itemType")
val itemType : String = "item"




        )