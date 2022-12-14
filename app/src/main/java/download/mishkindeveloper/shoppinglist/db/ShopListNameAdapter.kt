package download.mishkindeveloper.shoppinglist.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import download.mishkindeveloper.shoppinglist.R
import download.mishkindeveloper.shoppinglist.databinding.ShopListNameItemBinding
import download.mishkindeveloper.shoppinglist.entity.ShoppingListName

class ShopListNameAdapter(private val listener:Listener): ListAdapter<ShoppingListName, ShopListNameAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(view:View) : RecyclerView.ViewHolder(view){
        private val binding = ShopListNameItemBinding.bind(view)

        fun setData(shopListNameItem: ShoppingListName,listener: Listener) = with((binding)){
            tvShoppingListName.text = shopListNameItem.name
            tvTimeShoppingList.text = shopListNameItem.time

            itemView.setOnClickListener {

            }
            imDeleteShopping.setOnClickListener {
listener.deleteItem(shopListNameItem.id!!)
            }
            imEditShoppingList.setOnClickListener {

            }

        }
        companion object{
            fun create(parent:ViewGroup):ItemHolder{
                return  ItemHolder(
                    LayoutInflater.from(parent.context).
                    inflate(R.layout.shop_list_name_item,parent,false))
            }
        }
    }
    class ItemComparator : DiffUtil.ItemCallback<ShoppingListName>(){
        override fun areItemsTheSame(oldItem: ShoppingListName, newItem: ShoppingListName): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ShoppingListName, newItem: ShoppingListName): Boolean {
            return  oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position),listener)
    }

    interface Listener{
        fun deleteItem(id:Int)
        fun onClickItem(shoppingList: ShoppingListName)
        fun editItem(shoppingList: ShoppingListName)
    }
}