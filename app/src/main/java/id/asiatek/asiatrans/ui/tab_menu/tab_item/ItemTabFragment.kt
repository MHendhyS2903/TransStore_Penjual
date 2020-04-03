package id.asiatek.asiatrans.ui.tab_menu.tab_item

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bpdsulteng.jbk.realm.dao.AccountDao
import es.dmoral.toasty.Toasty

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.databinding.EtalaseTabFragmentBinding
import id.asiatek.asiatrans.utils.NetworkUtils
import id.asiatek.asiatrans.databinding.ItemTabFragmentBinding
import id.asiatek.asiatrans.model.etalase.DataItemEtalase
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.model.item.DataItem
import id.asiatek.asiatrans.navigator.ItemNavigator
import id.asiatek.asiatrans.ui.base.BaseFragment
import id.asiatek.asiatrans.ui.tab_menu.tab_etalase.EtalaseAdapter
import id.asiatek.asiatrans.ui.tab_menu.tab_etalase.EtalaseTabFragment
import id.asiatek.asiatrans.viewmodel.EtalaseTabViewModel
import id.asiatek.asiatrans.viewmodel.ItemTabViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.dialog_no_internet.*
import kotlinx.android.synthetic.main.etalase_tab_fragment.*
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
//        setupListener()
//        setupShowcase()
        adapter.setOnItemClickListener(this)
//        viewModel.getProfile()
        viewModel.getProductList()
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
//        btnTambahEtalase.setOnClickListener {
//            activity?.let{
//                val intent = Intent (it, id.asiatek.asiatrans.ui.etalase.EtalaseActivity::class.java)
//                it.startActivity(intent)
//            }
//        }
    }

    private fun setupShowcase() {
//        if(SharedPref.getFirstTimeMain()) {
//            builder = GuideView.Builder(baseActivity)
//                .setTitle("MENU")
//                .setTitleTextSize(14)
//                .setTitleTypeFace(Typeface.createFromAsset(baseActivity.getAssets(), "Quicksand-Bold.ttf"))
//                .setContentTextSize(12)
//                .setContentTypeFace(Typeface.createFromAsset(baseActivity.getAssets(), "Quicksand-Medium.ttf"))
//                .setContentText("Untuk memilih menu yang ada didalam aplikasi")
//                .setGravity(Gravity.center)
//                .setDismissType(DismissType.outside)
//                .setGuideListener {
//                    builder!!
//                        .setTitle("TAMBAH")
//                        .setContentText("Untuk menambah outlet")
//                        .setGuideListener {
//                            SharedPref.setFirstTimeMain(false)
//                            mGuideView = builder!!.build()
//                            mGuideView!!.updateGuideViewLocation()
//                        }
//                    mGuideView = builder!!.build()
//                    mGuideView!!.show()
//                }
//            mGuideView = builder!!.build()
//            mGuideView!!.show()
//        }
    }

    override fun onSuccessProfile(msg: MsgGmail) {
//        accountDao.deleteLogin()
//        msg.Value?.let { accountDao.addLogin(it) }
//        var name = accountDao.getLogin()!!.owner!!.split(' ').joinToString(" ") { it.capitalize() }
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
//        activity?.let{
//            val intent = Intent (LoginActivity::class.java).apply {
//                putExtra(id.asiatek.asiatrans.utils.AppConstants.KEY_REQUEST, request)
//            }
//            it.startActivity(intent)
//        }
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
