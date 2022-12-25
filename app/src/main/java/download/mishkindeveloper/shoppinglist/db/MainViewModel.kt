package download.mishkindeveloper.shoppinglist.db

import androidx.lifecycle.*
import download.mishkindeveloper.shoppinglist.entity.NoteItem
import download.mishkindeveloper.shoppinglist.entity.ShopListNameItem
import download.mishkindeveloper.shoppinglist.entity.ShoppingListItem
import kotlinx.coroutines.launch

class MainViewModel(dataBase: MainDataBase) :ViewModel() {
    val dao = dataBase.getDao()

    val allNotes:LiveData<List<NoteItem>> = dao.getAllNotes().asLiveData()
    val allShopListNameItem:LiveData<List<ShopListNameItem>> = dao.getAllShoppingListNames().asLiveData()

//для заметок
    fun insertNote(note: NoteItem) = viewModelScope.launch {
        dao.insertNote(note)
    }
    fun updateNote(note: NoteItem) = viewModelScope.launch {
        dao.updateNote(note)
    }
    fun DeleteNote(id: Int) = viewModelScope.launch {
        dao.deleteNote(id)
    }
//для шопинг листа
    fun insertShopListName(listName: ShopListNameItem) = viewModelScope.launch {
    dao.insertShopListName(listName)
}
    fun deleteShopListName(id: Int) = viewModelScope.launch {
        dao.deleteShopinglistName(id)
    }

    fun updateListName(shopListName: ShopListNameItem) = viewModelScope.launch {
        dao.updateListName(shopListName)
    }
//для всплывающих подсказок,сохраненных и покупок
fun insertShopItem(shoppingListItem: ShoppingListItem) = viewModelScope.launch {
    dao.insertItem(shoppingListItem)
}

    fun getAllItemsFromList(listId:Int): LiveData<List<ShoppingListItem>>{
        return dao.getAllShopListItems(listId).asLiveData()
    }

class MainViewModelFactory(val dataBase: MainDataBase) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dataBase) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModelClass")
    }
}
}