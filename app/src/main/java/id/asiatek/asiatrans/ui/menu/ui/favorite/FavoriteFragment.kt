package id.asiatek.asiatrans.ui.menu.ui.favorite

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.databinding.FavoriteFragmentBinding
import id.asiatek.asiatrans.model.account.DataAccountValue
import id.asiatek.asiatrans.model.account.MsgAccount
import id.asiatek.asiatrans.model.cart.MsgCart
import id.asiatek.asiatrans.model.item.MsgItem
import id.asiatek.asiatrans.model.wishlist.MsgWishlist
import id.asiatek.asiatrans.navigator.MenuNavigator
import id.asiatek.asiatrans.ui.base.BaseFragment
import id.asiatek.asiatrans.viewmodel.FavoriteFragmentViewModel
import kotlinx.android.synthetic.main.favorite_fragment.*
import id.asiatek.asiatrans.model.wishlist.WishlistRequest
import javax.inject.Inject

class FavoriteFragment : BaseFragment<FavoriteFragmentBinding, FavoriteFragmentViewModel>(), MenuNavigator {

    override fun onSuccessAccount(msg: MsgAccount) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessCart(msg: MsgCart) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessWishlist(msg: MsgWishlist) {
        try{
            if(msg.status == true){
                if(viewModel.getItems().isEmpty()){
                    rv_wishlist.visibility = View.GONE
                }else{
                    rv_wishlist.visibility = View.VISIBLE
                }
            }else{

            }
        }catch (e: Exception){
            Log.e(TAG, e.message)
        }
    }

    override fun onSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var binding: FavoriteFragmentBinding

    @Inject
    internal lateinit var viewModel: FavoriteFragmentViewModel

    @Inject
    internal lateinit var adapter: WishlistAdapter

    private var mHandler = Handler()
    private lateinit var mRunnable: Runnable

    companion object {
        val TAG = FavoriteFragment::class.java.simpleName
        fun newInstance(): FavoriteFragment {
            val fragment = FavoriteFragment()
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
        viewModel.getWishlist(WishlistRequest("MDg1Nzg2NTE4MDg4"))
    }

    private fun setupView() {
        rv_wishlist!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_wishlist.setHasFixedSize(false)
        rv_wishlist!!.adapter = adapter
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
        return BR.vmFavorite
    }
    override fun getLayoutId(): Int {
        return R.layout.favorite_fragment
    }
    override fun getViewModel(): FavoriteFragmentViewModel {
        return viewModel
    }

}
