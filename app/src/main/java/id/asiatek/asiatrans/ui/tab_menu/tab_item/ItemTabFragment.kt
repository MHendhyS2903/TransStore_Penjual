package id.asiatek.asiatrans.ui.tab_menu.tab_item

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bpdsulteng.jbk.realm.dao.AccountDao

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.utils.NetworkUtils
import id.asiatek.asiatrans.databinding.ItemTabFragmentBinding
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.model.item.DataItem
import id.asiatek.asiatrans.model.item.MsgItem
import id.asiatek.asiatrans.navigator.ItemNavigator
import id.asiatek.asiatrans.ui.base.BaseFragment
import id.asiatek.asiatrans.viewmodel.ItemTabViewModel
import id.asiatek.asiatrans.utils.AppConstants.*
import kotlinx.android.synthetic.main.activity_splash.*
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
//        viewModel.getProfile()
        viewModel.getProductList()
    }

    override fun onSuccessAdd(msg: MsgItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setupView() {
//        val adRequest = AdRequest.Builder().build()
////        val adRequest = AdRequest.Builder().addTestDevice("C3DCC65FC8143E4E30B0E79DA1032061").build()
//        adView.loadAd(adRequest)

//        nsv_outlet.isSmoothScrollingEnabled = true
        rv_items!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_items.setHasFixedSize(true)
        rv_items!!.adapter = adapter
    }

    private fun setupListener() {
        btnTambahBarang.setOnClickListener {
            activity?.let{
                val intent = Intent (it, ProductActivity::class.java)
                it.startActivity(intent)
            }
        }
    }

    override fun onSuccessProfile(msg: MsgGmail) {
    }

    override fun onSuccessUpdate(msg: MsgItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess() {
//        if(nsv_outlet != null){
//            nsv_outlet.smoothScrollTo(0,0)
//        if(viewModel.getProductList().isEmpty()){
//            lv_loading.visibility = View.GONE
//            rv_items.visibility = View.GONE
//            ll_no_internet.visibility = View.GONE
//        }
//        }
//        Toasty.error(baseActivity, "Masukan Nomor Handphone dan Password", Toast.LENGTH_SHORT, true).show()
    }

    override fun onError() {
        if(!NetworkUtils.isNetworkConnected(activity)){
            if(ll_shimmer_outlet != null){
                ll_shimmer_outlet.stopShimmerAnimation()
                lv_loading.visibility = View.GONE
                rv_items.visibility = View.GONE
//                ll_empty_outlet.visibility = View.GONE
                ll_no_internet.visibility = View.VISIBLE
            }
        }
    }

    override fun showLoading() {
//        ll_shimmer_outlet.startShimmerAnimation()
//        lv_loading.visibility = View.VISIBLE
//        rv_etalase_list.visibility = View.GONE
////        ll_empty_outlet.visibility = View.GONE
//        ll_no_internet.visibility = View.GONE
    }

    override fun hideLoading() {
        if(ll_shimmer_outlet != null) {
            ll_shimmer_outlet.stopShimmerAnimation()
            lv_loading.visibility = View.GONE
            rv_items.visibility = View.VISIBLE
//            ll_empty_outlet.visibility = View.GONE
            ll_no_internet.visibility = View.GONE
        }
    }

    override fun onItemClickEtalase(view: View?, obj: DataItem?) {
        refreshOutletList = true
        var request = obj!!
//        startActivity<EtalaseActivity>(KEY_REQUEST to request)
        activity?.let{
            val intent = Intent (it, ProductActivity::class.java).apply {
                putExtra(KEY_REQUEST, request)
            }
            it.startActivity(intent)
        }
    }

    override fun showMsg(msg: String) {

    }

    override fun onResume() {
        super.onResume()
        if(refreshOutletList) {
            viewModel.getProductList()
            refreshOutletList = false
        }
    }

    override fun getBindingVariable(): Int {
        return BR.vmItems
    }
    override fun getLayoutId(): Int {
        return R.layout.item_tab_fragment
    }
    override fun getViewModel(): ItemTabViewModel {
        return viewModel
    }
}
