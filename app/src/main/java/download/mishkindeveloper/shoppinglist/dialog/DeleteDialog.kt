package download.mishkindeveloper.shoppinglist.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import download.mishkindeveloper.shoppinglist.databinding.DeleteDialogBinding
import download.mishkindeveloper.shoppinglist.databinding.NewListShoppingDialogBinding

object DeleteDialog {
    fun showDialog(context: Context,listener: Listener){
        var dialog:AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = DeleteDialogBinding.inflate(LayoutInflater.from(context))

        builder.setView(binding.root)
        binding.apply {
            bDelete.setOnClickListener {
                    listener.onClick()
                dialog?.dismiss()
            }
            bCancel.setOnClickListener {
                dialog?.dismiss()
            }

        }
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }
    interface Listener{
        fun onClick(){

        }
    }
}