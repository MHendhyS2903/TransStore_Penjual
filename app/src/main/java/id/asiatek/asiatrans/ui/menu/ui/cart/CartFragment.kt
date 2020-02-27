package id.asiatek.asiatrans.ui.menu.ui.cart

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.UserManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.databinding.CartFragmentBinding
import id.asiatek.asiatrans.model.account.MsgAccount
import id.asiatek.asiatrans.model.item.MsgItem
import id.asiatek.asiatrans.model.wishlist.MsgWishlist
import id.asiatek.asiatrans.navigator.MenuNavigator
import id.asiatek.asiatrans.ui.base.BaseFragment
import id.asiatek.asiatrans.viewmodel.CartFragmentViewModel
import id.asiatek.asiatrans.model.cart.CartListRequest
import id.asiatek.asiatrans.model.cart.DataCartOrder
import id.asiatek.asiatrans.model.cart.MsgCart
import kotlinx.android.synthetic.main.activity_menu_manual.*
import kotlinx.android.synthetic.main.cart_fragment.*
import kotlinx.android.synthetic.main.dialog_spinner_search.*
import kotlinx.android.synthetic.main.item_cart.*
import kotlinx.android.synthetic.main.item_cart.view.*
import javax.inject.Inject

class CartFragment : BaseFragment<CartFragmentBinding, CartFragmentViewModel>(), CartAdapter.OnItemClickListener, MenuNavigator {

    override fun onItemClickItem(view: View?, obj: DataCartOrder?) {
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
        try{
            if(msg.status == true){
                if(viewModel.getItemsOrder().isEmpty()){
                    rv_cart.visibility = View.GONE
                }else{
                    rv_cart.visibility = View.VISIBLE
                }
            }else{

            }
        }catch (e: Exception){
            Log.e(TAG, e.message)
        }
    }

    override fun onSuccessWishlist(msg: MsgWishlist) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates
    }

    override fun onSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var binding: CartFragmentBinding

    @Inject
    internal lateinit var viewModel: CartFragmentViewModel

    @Inject
    internal lateinit var adapter: CartAdapter

    private var mHandler = Handler()
    private lateinit var mRunnable: Runnable

    companion object {
        val TAG = CartFragment::class.java.simpleName
        fun newInstance(): CartFragment {
            val fragment = CartFragment()
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
        viewModel.getCartList(CartListRequest("MDg1Nzg2NTE4MDg4"))
    }

    private fun setupView() {
        rv_cart!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_cart.setHasFixedSize(false)
        rv_cart!!.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding
        setupView()
    }


    override fun onSuccessDisplay(msg: MsgItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        return BR.vmCart
    }
    override fun getLayoutId(): Int {
        return R.layout.cart_fragment
    }
    override fun getViewModel(): CartFragmentViewModel {
        return viewModel
    }

}
