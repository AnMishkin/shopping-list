package download.mishkindeveloper.shoppinglist.activities

import android.app.Application
import download.mishkindeveloper.shoppinglist.db.MainDataBase

class MainApp :Application(){
    val database by lazy { MainDataBase.getDataBase(this) }

}