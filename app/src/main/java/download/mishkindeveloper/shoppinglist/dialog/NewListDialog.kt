package download.mishkindeveloper.shoppinglist.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import download.mishkindeveloper.shoppinglist.databinding.NewListShoppingDialogBinding

object NewListDialog {
    fun showDialog(context: Context,listener: Listener){
        var dialog:AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = NewListShoppingDialogBinding.inflate(LayoutInflater.from(context))

        builder.setView(binding.root)
        binding.apply {
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