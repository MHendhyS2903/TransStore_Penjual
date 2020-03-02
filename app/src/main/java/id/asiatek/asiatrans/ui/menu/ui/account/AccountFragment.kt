package id.asiatek.asiatrans.ui.menu.ui.account

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.databinding.AccountFragmentBinding
import id.asiatek.asiatrans.model.account.MsgAccount
import id.asiatek.asiatrans.model.account.AccountRequest
import id.asiatek.asiatrans.model.cart.MsgCart
import id.asiatek.asiatrans.model.item.MsgItem
import id.asiatek.asiatrans.model.wishlist.MsgWishlist
import id.asiatek.asiatrans.navigator.MenuNavigator
import id.asiatek.asiatrans.ui.SharedPreference
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.ui.base.BaseFragment
import id.asiatek.asiatrans.viewmodel.AccountFragmentViewModel
import kotlinx.android.synthetic.main.account_fragment.*
import javax.inject.Inject

class AccountFragment : BaseFragment<AccountFragmentBinding, AccountFragmentViewModel>(), MenuNavigator {
    override fun onSuccessAccount(msg: MsgAccount) {
        try{
            if(msg.status == true){
                if(viewModel.getItems().isEmpty()){
                    rv_account.visibility = View.GONE
                }else{
                    rv_account.visibility = View.VISIBLE
                }
            }else{

            }
        }catch (e: Exception){
            Log.e(TAG, e.message)
        }
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

    private lateinit var binding: AccountFragmentBinding

    @Inject
    internal lateinit var viewModel: AccountFragmentViewModel

    @Inject
    internal lateinit var adapter: AccountAdapter

    private var mHandler = Handler()
    private lateinit var mRunnable: Runnable

    companion object {
        val TAG = AccountFragment::class.java.simpleName
        fun newInstance(): AccountFragment {
            val fragment = AccountFragment()
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
        var sharedPreference: SharedPreference = SharedPreference(baseActivity)
        var cellular = sharedPreference.getValueString("account")
        viewModel.getAccount(AccountRequest(
                "dGVzdDEyMzYyODU5MjE3MDIwMjZtLmhlbmRoeS5zQGdtYWlsLmNvbTQyOTE=",
                "{\"limit\":20,\"page\":1,\"field\":\"id\",\"direction\":\"asc\"}",
                "[{\"field\":\"cellular\",\"op\":\"eq\",\"value\":\""+cellular+"\"}]"
            )
        )
    }

    private fun setupView() {
        rv_account!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_account.setHasFixedSize(false)
        rv_account!!.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding
        setupView()
        var sharedPreference: SharedPreference = SharedPreference(baseActivity)

        btnLogout.setOnClickListener {
            sharedPreference.removeValue("account")
            Toast.makeText(baseActivity,"Logout", Toast.LENGTH_SHORT ).show()
            val intent = Intent(activity, LoginActivity::class.java)
            activity?.startActivity(intent)
            }
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
        return BR.vmAccount
    }
    override fun getLayoutId(): Int {
        return R.layout.account_fragment
    }
    override fun getViewModel(): AccountFragmentViewModel {
        return viewModel
    }

//    override fun onItemClickItem(view: View?, obj: DataItemValue?) {
//
//    }
}
