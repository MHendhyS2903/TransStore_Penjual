package id.asiatek.asiatrans.ui.tab_menu.tab_etalase

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bpdsulteng.jbk.realm.dao.AccountDao

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.data.prefs.SharedPref
import  id.asiatek.asiatrans.utils.NetworkUtils
import id.asiatek.asiatrans.databinding.EtalaseTabFragmentBinding
import id.asiatek.asiatrans.model.etalase.DataItemEtalase
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.navigator.EtalaseNavigator
import id.asiatek.asiatrans.ui.base.BaseFragment
import id.asiatek.asiatrans.ui.etalase.EtalaseActivity
import id.asiatek.asiatrans.utils.AppConstants.KEY_REQUEST
import id.asiatek.asiatrans.viewmodel.EtalaseTabViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.dialog_no_internet.*
import kotlinx.android.synthetic.main.etalase_tab_fragment.*
import kotlinx.android.synthetic.main.progress_loading_outlet.*
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity
import javax.inject.Inject

class EtalaseTabFragment : BaseFragment<EtalaseTabFragmentBinding, EtalaseTabViewModel>(), EtalaseNavigator, EtalaseAdapter.OnItemClickListener {

    private lateinit var binding: EtalaseTabFragmentBinding
    @Inject
    internal lateinit var viewModel: EtalaseTabViewModel
    @Inject
    internal lateinit var adapter: EtalaseAdapter
    val accountDao = AccountDao()
    var refreshOutletList : Boolean = false
    /*SHOWCASE*/
    private var mGuideView: GuideView? = null
    private var builder: GuideView.Builder? = null
    /*REFRESH*/
    private var mHandler = Handler()
    private lateinit var mRunnable: Runnable

    companion object {
        fun newInstance() = EtalaseTabFragment()
        val TAG = EtalaseTabFragment::class.java.simpleName
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
//        setupShowcase()
        adapter.setOnItemClickListener(this)
//        viewModel.getProfile()
        viewModel.getEtalaseList()
    }

    private fun setupView() {
//        val adRequest = AdRequest.Builder().build()
////        val adRequest = AdRequest.Builder().addTestDevice("C3DCC65FC8143E4E30B0E79DA1032061").build()
//        adView.loadAd(adRequest)

//        nsv_outlet.isSmoothScrollingEnabled = true
        rv_etalase_list!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_etalase_list.setHasFixedSize(true)
        rv_etalase_list!!.adapter = adapter
    }

    private fun setupListener() {
        btnTambahEtalase.setOnClickListener {
            activity?.let{
                val intent = Intent (it, EtalaseActivity::class.java)
                it.startActivity(intent)
            }
        }
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
            if(viewModel.getItemsEtalase().isEmpty()){
//                lv_loading.visibility = View.GONE
                rv_etalase_list.visibility = View.GONE
//                ll_no_internet.visibility = View.GONE
            }
//        }
    }

    override fun onError() {
        if(!NetworkUtils.isNetworkConnected(activity)){
            if(ll_shimmer_outlet != null){
                ll_shimmer_outlet.stopShimmerAnimation()
                lv_loading.visibility = View.GONE
                rv_etalase_list.visibility = View.GONE
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
            rv_etalase_list.visibility = View.VISIBLE
//            ll_empty_outlet.visibility = View.GONE
            ll_no_internet.visibility = View.GONE
        }
    }

    override fun onItemClickEtalase(view: View?, obj: DataItemEtalase?) {
        refreshOutletList = true
        var request = obj!!
//        startActivity<EtalaseActivity>(KEY_REQUEST to request)
        activity?.let{
            val intent = Intent (it, EtalaseActivity::class.java).apply {
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
            viewModel.getEtalaseList()
            refreshOutletList = false
        }
    }

    override fun getBindingVariable(): Int {
        return BR.vmEtalase
    }
    override fun getLayoutId(): Int {
        return R.layout.etalase_tab_fragment
    }
    override fun getViewModel(): EtalaseTabViewModel {
        return viewModel
    }
}
