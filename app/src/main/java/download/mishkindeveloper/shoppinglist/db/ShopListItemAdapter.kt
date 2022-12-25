package download.mishkindeveloper.shoppinglist.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import download.mishkindeveloper.shoppinglist.R
import download.mishkindeveloper.shoppinglist.databinding.ShopListItemBinding
import download.mishkindeveloper.shoppinglist.databinding.ShopListNameItemBinding
import download.mishkindeveloper.shoppinglist.entity.ShopListNameItem

import download.mishkindeveloper.shoppinglist.entity.ShoppingListItem

class ShopListItemAdapter(private val listener:Listener): ListAdapter<ShoppingListItem, ShopListItemAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(val view:View) : RecyclerView.ViewHolder(view){


        fun setItemData(shopListItem: ShoppingListItem, listener: Listener){
            val binding = ShopListItemBinding.bind(view)
            binding.apply {
                tvName.text = shopListItem.name
                tvInfo.text = shopListItem.itemInfo
                tvInfo.visibility = infoVisibility(shopListItem)
            }
            }

            fun setLibraryData(shopListItem: ShoppingListItem, listener: Listener){

            }


            fun infoVisibility(shopListItem: ShoppingListItem):Int{
               return if (shopListItem.itemInfo.isNullOrEmpty()){
                    View.GONE
                    } else {
                        View.VISIBLE
                    }
            }
        companion object{
            fun createShopItem(parent:ViewGroup):ItemHolder{
                return  ItemHolder(
                    LayoutInflater.from(parent.context).
                    inflate(R.layout.shop_list_item,parent,false))
            }
            fun createLibraryItem(parent:ViewGroup):ItemHolder{
                return  ItemHolder(
                    LayoutInflater.from(parent.context).
                    inflate(R.layout.shop_list_name_item,parent,false))
            }

        }
    }

    class ItemComparator : DiffUtil.ItemCallback<ShoppingListItem>(){
        override fun areItemsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean {
            return  oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return if (viewType==0)
            ItemHolder.createShopItem(parent)
                else
                    ItemHolder.createLibraryItem(parent)

    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        if (getItem(position).itemType==0) {
            holder.setItemData(getItem(position), listener)
        } else{
            holder.setLibraryData(getItem(position), listener)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType
    }

    interface Listener{
        fun deleteItem(id:Int)
        fun onClickItem(shopListNameItem: ShopListNameItem)
        fun editItem(shopListNameItem: ShopListNameItem)
    }
}