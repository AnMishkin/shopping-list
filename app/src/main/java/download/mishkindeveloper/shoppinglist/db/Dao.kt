package download.mishkindeveloper.shoppinglist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import download.mishkindeveloper.shoppinglist.entity.NoteItem
import download.mishkindeveloper.shoppinglist.entity.ShopListNameItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    //Для заметок
    @Query("SELECT * FROM note_list")
    fun getAllNotes():Flow<List<NoteItem>>

    @Query("DELETE FROM note_list WHERE id IS :id")
    suspend fun deleteNote(id:Int)

    @Insert
    suspend fun insertNote(note: NoteItem)

    @Update
    suspend fun updateNote(note:NoteItem)


    //Для шопинг листа
    @Insert
    suspend fun insertShopListName(name:ShopListNameItem)

    @Query("SELECT * FROM shopping_list_names")
    fun getAllShoppingListNames():Flow<List<ShopListNameItem>>

    @Query("DELETE FROM shopping_list_names WHERE id IS :id")
    suspend fun deleteShopinglistName(id:Int)

    @Update
    suspend fun updateListName(shopListNameItem: ShopListNameItem)
}