package id.asiatek.asiatrans.ui.tab_menu.tab_item

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bpdsulteng.jbk.realm.dao.AccountDao

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.utils.NetworkUtils
import id.asiatek.asiatrans.databinding.ItemTabFragmentBinding
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.model.item.DataItem
import id.asiatek.asiatrans.navigator.ItemNavigator
import id.asiatek.asiatrans.ui.base.BaseFragment
import id.asiatek.asiatrans.viewmodel.ItemTabViewModel
import kotlinx.android.synthetic.main.dialog_no_internet.*
import kotlinx.android.synthetic.main.item_tab_fragment.*
import kotlinx.android.synthetic.main.progress_loading_outlet.*
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import javax.inject.Inject

class ItemTabFragment : BaseFragment<ItemTabFragmentBinding, ItemTabViewModel>(), ItemNavigator, ItemAdapter.OnItemClickListener {

    private lateinit var binding: ItemTabFragmentBinding
    @Inject
    internal lateinit var viewModel: ItemTabViewModel
    @Inject
    internal lateinit var adapter: ItemAdapter
    val accountDao = AccountDao()
    var refreshOutletList : Boolean = false
    /*SHOWCASE*/
    private var mGuideView: GuideView? = null
    private var builder: GuideView.Builder? = null
    /*REFRESH*/
    private var mHandler = Handler()
    private lateinit var mRunnable: Runnable

    companion object {
        fun newInstance() = ItemTabFragment()
        val TAG = ItemTabFragment::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {}
        viewModel.navigator = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding
        setupView()
        setupListener()
        adapter.setOnItemClickListener(this)
        viewModel.getProfile()
        viewModel.getOutletList()
    }

    private fun setupView() {
        rv_items!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_items.setHasFixedSize(true)
        rv_items!!.adapter = adapter
    }

    private fun setupListener() {
        btnTambahBarang.setOnClickListener {
            refreshOutletList = true
//            startActivity<LoginActivity>()
        }
    }

    override fun onSuccessProfile(msg: MsgGmail) {
        accountDao.deleteLogin()
        msg.Value?.let { accountDao.addLogin(it) }
        var name = accountDao.getLogin()!!.owner!!.split(' ').joinToString(" ") { it.capitalize() }
    }

    override fun onSuccess() {
        if(viewModel.getItems().isEmpty()){
            rv_items.visibility = View.GONE
            ll_no_internet.visibility = View.GONE
        }
    }

    override fun onError() {
        if(!NetworkUtils.isNetworkConnected(activity)){
            if(ll_shimmer_outlet != null){
                ll_shimmer_outlet.stopShimmerAnimation()
                rv_items.visibility = View.GONE
                ll_no_internet.visibility = View.VISIBLE
            }
        }
    }

    override fun showLoading() {
        ll_shimmer_outlet.startShimmerAnimation()
        rv_items.visibility = View.GONE
        ll_no_internet.visibility = View.GONE
    }

    override fun hideLoading() {
        if(ll_shimmer_outlet != null) {
            ll_shimmer_outlet.stopShimmerAnimation()
            rv_items.visibility = View.VISIBLE
            ll_no_internet.visibility = View.GONE
        }
    }

    override fun onItemClickOutlet(view: View?, obj: DataItem?) {
        refreshOutletList = true
        var request = obj!!
//        startActivity<OutletActivity>(KEY_REQUEST to request)
    }

    override fun showMsg(msg: String) {

    }

    override fun onResume() {
        super.onResume()
        if(refreshOutletList) {
            viewModel.getOutletList()
            refreshOutletList = false
        }
    }

    override fun getBindingVariable(): Int {
        return BR.vmhome
    }
    override fun getLayoutId(): Int {
        return R.layout.item_tab_fragment
    }
    override fun getViewModel(): ItemTabViewModel {
        return viewModel
    }
}
