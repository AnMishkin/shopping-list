package download.mishkindeveloper.shoppinglist.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import download.mishkindeveloper.shoppinglist.R
import download.mishkindeveloper.shoppinglist.databinding.ShopListNameItemBinding
import download.mishkindeveloper.shoppinglist.entity.ShopListNameItem

class ShopListNameAdapter(private val listener:Listener): ListAdapter<ShopListNameItem, ShopListNameAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(view:View) : RecyclerView.ViewHolder(view){
        private val binding = ShopListNameItemBinding.bind(view)

        fun setData(shopListNameItem: ShopListNameItem, listener: Listener) = with((binding)){
            tvShoppingListName.text = shopListNameItem.name
            tvTimeShoppingList.text = shopListNameItem.time

            itemView.setOnClickListener {
            listener.onClickItem(shopListNameItem)
            }
            imDeleteShopping.setOnClickListener {
            listener.deleteItem(shopListNameItem.id!!)
            }
            imEditShoppingList.setOnClickListener {
                listener.editItem(shopListNameItem)
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
    class ItemComparator : DiffUtil.ItemCallback<ShopListNameItem>(){
        override fun areItemsTheSame(oldItem: ShopListNameItem, newItem: ShopListNameItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ShopListNameItem, newItem: ShopListNameItem): Boolean {
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
        fun onClickItem(shopListNameItem: ShopListNameItem)
        fun editItem(shopListNameItem: ShopListNameItem)
    }
}