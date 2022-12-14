package download.mishkindeveloper.shoppinglist.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import download.mishkindeveloper.shoppinglist.activities.MainApp
import download.mishkindeveloper.shoppinglist.databinding.FragmentShopListNamesBinding
import download.mishkindeveloper.shoppinglist.db.MainViewModel
import download.mishkindeveloper.shoppinglist.dialog.NewListDialog
import download.mishkindeveloper.shoppinglist.entity.ShoppingList
import download.mishkindeveloper.shoppinglist.utils.TimeManager


class ShopListNamesFragment : BaseFragment(){
    private lateinit var binding:FragmentShopListNamesBinding



    private val mainViewModel:MainViewModel by activityViewModels{
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }
    override fun onClickNew() {
    NewListDialog.showDialog(activity as AppCompatActivity,object: NewListDialog.Listener{
        override fun onClick(name: String) {
            //Log.d("MyLog","$name")
            //super.onClick(name)
            val shoppingList = ShoppingList(
                null,
                name,
                TimeManager.getCurrentTime(),
                0,
                0,
                ""
            )
mainViewModel.insertShopListName(shoppingList)
        }

    } )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopListNamesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        observer()
    }

    private fun initRcView() = with(binding){

    }

    private fun observer(){
        mainViewModel.alShoppingList.observe(viewLifecycleOwner,{

        })
    }



    companion object {


        @JvmStatic
        fun newInstance() = ShopListNamesFragment()
    }




}