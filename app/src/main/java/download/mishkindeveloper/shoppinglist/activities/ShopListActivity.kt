package download.mishkindeveloper.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import download.mishkindeveloper.shoppinglist.R
import download.mishkindeveloper.shoppinglist.databinding.ActivityShopListBinding
import download.mishkindeveloper.shoppinglist.db.MainViewModel
import download.mishkindeveloper.shoppinglist.entity.ShopListNameItem


class ShopListActivity : AppCompatActivity() {
    lateinit private var binding:ActivityShopListBinding
    private var shopListNameItem :ShopListNameItem? = null

    private val mainViewModel:MainViewModel by viewModels{
        MainViewModel.MainViewModelFactory((applicationContext as MainApp).database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shop_list_menu,menu)
        return true
    }

    private fun init(){
        shopListNameItem = intent.getSerializableExtra(SHOP_LIST_NAME) as ShopListNameItem
        binding.tvTest.text = shopListNameItem?.name
    }


companion object{
    const val SHOP_LIST_NAME = "shop_list_name"
}
}