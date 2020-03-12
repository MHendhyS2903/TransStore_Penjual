package id.asiatek.asiatrans.ui.tab_menu.tab_home

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.bpdsulteng.jbk.realm.dao.AccountDao

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.databinding.HomeTabFragmentBinding
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.navigator.HomeNavigator
import id.asiatek.asiatrans.ui.base.BaseFragment
import id.asiatek.asiatrans.viewmodel.HomeTabViewModel
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
        viewModel.getProfile()
    }

    override fun onSuccessProfile(msg: MsgGmail) {
        accountDao.deleteLogin()
        msg.Value?.let { accountDao.addLogin(it) }
        var name = accountDao.getLogin()!!.owner!!.split(' ').joinToString(" ") { it.capitalize() }
//        tv_doku_balance.text = accountDao.getLogin()?.balance?.toInt().toString()
//        tv_name.text = name
//        if(accountDao.getLogin()!!.dokuid.isNullOrEmpty()){
//            doku_nonactive.visibility = View.VISIBLE
//            doku_active.visibility = View.GONE
//        }else{
//            doku_nonactive.visibility = View.GONE
//            doku_active.visibility = View.VISIBLE
//        }
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
