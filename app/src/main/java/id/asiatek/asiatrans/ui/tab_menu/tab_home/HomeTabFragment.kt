package id.asiatek.asiatrans.ui.tab_menu.tab_home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.bpdsulteng.jbk.realm.dao.AccountDao
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.databinding.HomeTabFragmentBinding
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.ui.account.AccountProfileActivity
import id.asiatek.asiatrans.data.prefs.SharedPref
import id.asiatek.asiatrans.navigator.HomeNavigator
import id.asiatek.asiatrans.ui.base.BaseFragment
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.viewmodel.HomeTabViewModel
import kotlinx.android.synthetic.main.home_tab_fragment.*
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import javax.inject.Inject

class HomeTabFragment : BaseFragment<HomeTabFragmentBinding, HomeTabViewModel>(), HomeNavigator {


    private lateinit var binding: HomeTabFragmentBinding

    @Inject
    internal lateinit var viewModel: HomeTabViewModel

    val accountDao = AccountDao()

    var refreshOutletList : Boolean = false
    /*SHOWCASE*/
    private var mGuideView: GuideView? = null
    private var builder: GuideView.Builder? = null
    /*REFRESH*/
    private var mHandler = Handler()
    private lateinit var mRunnable: Runnable

    companion object {
        fun newInstance() = HomeTabFragment()
        val TAG = HomeTabFragment::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {}
        viewModel.navigator = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding

        btnEditProfile.setOnClickListener {
            activity?.let{
                val intent = Intent (it, AccountProfileActivity::class.java)
                it.startActivity(intent)
            }
        }
    }

    override fun toLogOut() {
        AlertDialog.Builder(activity)
            .setMessage("Anda yakin ingin keluar dari akun ini ?")
            .setPositiveButton("Ya") { v, d ->
                accountDao.deleteLogin()
                SharedPref.deleteToken()
                val alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(activity);
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()
                var googleSignInClient = activity?.let { GoogleSignIn.getClient(it, gso) }
                if (alreadyloggedAccount != null) {
                    googleSignInClient!!.signOut()
                }
                activity!!.finish()
                activity?.let{
                    val intent = Intent (it, LoginActivity::class.java)
                    it.startActivity(intent)
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onSuccess() {

    }

    override fun onError() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMsg(msg: String) {

    }

    override fun getBindingVariable(): Int {
        return BR.vmhome
    }
    override fun getLayoutId(): Int {
        return R.layout.home_tab_fragment
    }
    override fun getViewModel(): HomeTabViewModel {
        return viewModel
    }
}
