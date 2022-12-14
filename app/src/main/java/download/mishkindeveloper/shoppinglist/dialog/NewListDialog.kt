package download.mishkindeveloper.shoppinglist.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import download.mishkindeveloper.shoppinglist.R
import download.mishkindeveloper.shoppinglist.databinding.NewListShoppingDialogBinding

object NewListDialog {
    fun showDialog(context: Context, listener: Listener,name: String){
        var dialog:AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = NewListShoppingDialogBinding.inflate(LayoutInflater.from(context))

        builder.setView(binding.root)
        binding.apply {
            edNewListShoppingName.setText(name)
            if (name.isNotEmpty()){
                bCreateNewListShop.text = context.getString(R.string.button_for_update_name_shop_list_item)
                titleNewItem.text = context.getString(R.string.enter_new_name_shopping_list)
                titleNewItem.textSize = 18F
            }
            bCreateNewListShop.setOnClickListener {
                val listName = edNewListShoppingName.text.toString()
                if (listName.isNotEmpty()){
                    listener.onClick(listName)
                }
                dialog?.dismiss()
            }
        }
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }
    interface Listener{
        fun onClick(name:String){

        }
    }
}