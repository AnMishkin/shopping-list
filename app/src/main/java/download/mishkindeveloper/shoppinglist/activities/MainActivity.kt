package download.mishkindeveloper.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import download.mishkindeveloper.shoppinglist.R
import download.mishkindeveloper.shoppinglist.databinding.ActivityMainBinding
import download.mishkindeveloper.shoppinglist.databinding.FragmentNoteBinding
import download.mishkindeveloper.shoppinglist.dialog.NewListDialog
import download.mishkindeveloper.shoppinglist.fragments.FragmentManager
import download.mishkindeveloper.shoppinglist.fragments.NoteFragment
import download.mishkindeveloper.shoppinglist.fragments.ShopListNamesFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),NewListDialog.Listener {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FragmentManager.setFragment(NoteFragment.newInstance(),this)

        initClickBar()





    }

private fun initClickBar(){

    bNav.background = null

    new_item.setOnClickListener {
    FragmentManager.currentFrag?.onClickNew()
    }

    binding.bNav.setOnItemSelectedListener { //item ->

        when (it.itemId) {
            R.id.notes -> {
                FragmentManager.setFragment(NoteFragment.newInstance(),this)
            }
            R.id.shop_list -> {
                FragmentManager.setFragment(ShopListNamesFragment.newInstance(),this)
            }
            R.id.settings -> {
                Toast.makeText(this, "Настройки!", Toast.LENGTH_SHORT).show()
            }
        }
        true
    }
}

    override fun onClick(name: String) {

    }
}


