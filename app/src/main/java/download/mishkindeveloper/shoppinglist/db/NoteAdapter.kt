package download.mishkindeveloper.shoppinglist.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import download.mishkindeveloper.shoppinglist.R
import download.mishkindeveloper.shoppinglist.databinding.NoneListItemBinding
import download.mishkindeveloper.shoppinglist.entity.NoteItem
import download.mishkindeveloper.shoppinglist.utils.HtmlManager

class NoteAdapter(private val listener:Listener): ListAdapter<NoteItem, NoteAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(view:View) : RecyclerView.ViewHolder(view){
        private val binding = NoneListItemBinding.bind(view)

        fun setData(note: NoteItem,listener:Listener) = with((binding)){
            tvTitle.text = note.title
            tvDiscription.text = HtmlManager.getFromHtml(note.content).trim()
            tvTime.text = note.time
            itemView.setOnClickListener {
                listener.onClickItem(note)
            }
            imDelete.setOnClickListener {
                listener.deleteItem(note.id!!)
            }
        }
        companion object{
            fun create(parent:ViewGroup):ItemHolder{
                return  ItemHolder(
                    LayoutInflater.from(parent.context).
                    inflate(R.layout.none_list_item,parent,false))
            }
        }
    }
    class ItemComparator : DiffUtil.ItemCallback<NoteItem>(){
        override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
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
        fun onClickItem(note: NoteItem)
    }
}