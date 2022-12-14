package download.mishkindeveloper.shoppinglist.db

import androidx.lifecycle.*
import download.mishkindeveloper.shoppinglist.entity.NoteItem
import download.mishkindeveloper.shoppinglist.entity.ShoppingList
import kotlinx.coroutines.launch

class MainViewModel(dataBase: MainDataBase) :ViewModel() {
    val dao = dataBase.getDao()

    val allNotes:LiveData<List<NoteItem>> = dao.getAllNotes().asLiveData()
    val alShoppingList:LiveData<List<ShoppingList>> = dao.getAllShoppingListNames().asLiveData()

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
fun insertShopListName(listName: ShoppingList) = viewModelScope.launch {
    dao.insertShopListName(listName)
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