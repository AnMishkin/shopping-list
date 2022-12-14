package download.mishkindeveloper.shoppinglist.fragments

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import download.mishkindeveloper.shoppinglist.R

object FragmentManager {
    var currentFrag: BaseFragment? = null

    fun setFragment(newFrag: BaseFragment, activity: AppCompatActivity) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.placeHolder, newFrag)
        transaction.commit()
        currentFrag = newFrag
        Log.d("MyLog","currentfrag = ${currentFrag.toString()}, newFrag = ${newFrag.toString()}")
    }
}