package download.mishkindeveloper.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnActionExpandListener
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import download.mishkindeveloper.shoppinglist.R
import download.mishkindeveloper.shoppinglist.databinding.ActivityShopListBinding
import download.mishkindeveloper.shoppinglist.db.MainViewModel
import download.mishkindeveloper.shoppinglist.db.ShopListItemAdapter
import download.mishkindeveloper.shoppinglist.db.ShopListNameAdapter
import download.mishkindeveloper.shoppinglist.entity.ShopListNameItem
import download.mishkindeveloper.shoppinglist.entity.ShoppingListItem


class ShopListActivity : AppCompatActivity() , ShopListItemAdapter.Listener{
    lateinit private var binding:ActivityShopListBinding
    private var shopListNameItem :ShopListNameItem? = null
    private lateinit var saveItem:MenuItem
    private var edItem : EditText? = null
    private var adapter:ShopListItemAdapter? = null

    private val mainViewModel:MainViewModel by viewModels{
        MainViewModel.MainViewModelFactory((applicationContext as MainApp).database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initRcView()
        listItemObserver()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shop_list_menu,menu)
        saveItem = menu?.findItem(R.id.save_item)!!
        val newItem = menu.findItem(R.id.new_item)
        edItem = newItem.actionView?.findViewById(R.id.edNewShopItem) as EditText
        newItem.setOnActionExpandListener(expandActionViev())
        saveItem.isVisible=false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save_item){
            addNewShopItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addNewShopItem(){
        if (edItem?.text.toString().isEmpty()) return
        val item = ShoppingListItem(
            null,
            edItem?.text.toString(),
            null,
            0,
            shopListNameItem?.id!!,
            0
        )
        mainViewModel.insertShopItem(item)
        edItem?.setText("")
    }

    private fun listItemObserver(){
        mainViewModel.getAllItemsFromList(shopListNameItem?.id!!).observe(this,{
        adapter?.submitList(it)
        })
    }

    private fun initRcView() = with(binding){
        adapter = ShopListItemAdapter(this@ShopListActivity)
rcView.layoutManager = LinearLayoutManager(this@ShopListActivity)
        rcView.adapter = adapter
    }


    private fun expandActionViev():MenuItem.OnActionExpandListener{
        return object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                saveItem.isVisible=true
            return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                saveItem.isVisible=false
                invalidateOptionsMenu()
                return true
            }

        }
    }

    private fun init(){
        shopListNameItem = intent.getSerializableExtra(SHOP_LIST_NAME) as ShopListNameItem
        binding.tvTest.text = shopListNameItem?.name
    }


companion object{
    const val SHOP_LIST_NAME = "shop_list_name"
}

    override fun deleteItem(id: Int) {

    }

    override fun onClickItem(shopListNameItem: ShopListNameItem) {

    }

    override fun editItem(shopListNameItem: ShopListNameItem) {

    }
}