package id.asiatek.asiatrans.ui.menu.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.view.RxView
import es.dmoral.toasty.Toasty

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.model.item.ItemRequest
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.databinding.HomeFragmentBinding
import id.asiatek.asiatrans.model.account.MsgAccount
import id.asiatek.asiatrans.model.cart.MsgCart
import id.asiatek.asiatrans.model.item.DataItemValue
import id.asiatek.asiatrans.model.item.MsgItem
import id.asiatek.asiatrans.model.wishlist.MsgWishlist
import id.asiatek.asiatrans.navigator.MenuNavigator
import id.asiatek.asiatrans.ui.base.BaseFragment
import id.asiatek.asiatrans.viewmodel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.dialog_spinner_search.*
import id.asiatek.asiatrans.ui.detail.ItemDetailActivity
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeFragmentBinding, HomeFragmentViewModel>(), HomeAdapter.OnItemClickListener, MenuNavigator {
    override fun onItemClickItem(view: View?, obj: DataItemValue?) {
        when(view!!.id) {
            R.id.btnPlus -> {
                Toasty.success(context!!, "plus", Toast.LENGTH_LONG).show()
                Log.d(TAG, "test")
            }
            R.id.btnMinus -> Toasty.success(context!!, "minus", Toast.LENGTH_LONG).show()
            else
            -> Toasty.success(context!!, "else", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSuccessAccount(msg: MsgAccount) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessCart(msg: MsgCart) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessWishlist(msg: MsgWishlist) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var binding: HomeFragmentBinding

    @Inject
    internal lateinit var viewModel: HomeFragmentViewModel

    @Inject
    internal lateinit var adapter: HomeAdapter

    private var mHandler = Handler()
    private lateinit var mRunnable: Runnable

    companion object {
        val TAG = HomeFragment::class.java.simpleName
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {}
        viewModel.navigator = this

        params()
    }



    private fun params(){
        viewModel.getItemList(ItemRequest(
            "asjkldjlkajskldui212khkjdasd",
            "{\"limit\":20,\"page\":1,\"field\":\"id\",\"direction\":\"asc\"}",
            "[{\"field\":\"status\",\"op\":\"eq\",\"value\":\"CREATED\"}]"
        )
        )
    }

    private fun setupView() {
        rv_home!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_home.setHasFixedSize(false)
        rv_home!!.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding
        setupView()
    }

    override fun onSuccessDisplay(msg: MsgItem) {
        try{
            if(msg.status == true){
                if(viewModel.getItems().isEmpty()){
                    rv_home.visibility = View.GONE
                }else{
                    rv_home.visibility = View.VISIBLE
                }
            }else{

            }
        }catch (e: Exception){
            Log.e(TAG, e.message)
        }
    }

    override fun onErrorDisplay() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMsg(msg: String) {

    }

    override fun getBindingVariable(): Int {
        return BR.vmHome
    }
    override fun getLayoutId(): Int {
        return R.layout.home_fragment
    }
    override fun getViewModel(): HomeFragmentViewModel {
        return viewModel
    }

//    override fun onItemClickItem(view: View?, obj: DataItemValue?) {
//
//    }
}
