package id.asiatek.asiatrans.ui.tab_menu

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bpdsulteng.jbk.realm.dao.AccountDao
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.databinding.ActivityMainTabBinding
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.navigator.MainNavigator
import id.asiatek.asiatrans.ui.base.BaseActivity
import id.asiatek.asiatrans.viewmodel.MainTabViewModel
import kotlinx.android.synthetic.main.activity_main_tab.*
import javax.inject.Inject

class MainTabActivity : BaseActivity<ActivityMainTabBinding , MainTabViewModel>(), MainNavigator,
    HasSupportFragmentInjector {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    private lateinit var binding: ActivityMainTabBinding
    @Inject
    internal lateinit var viewModel: MainTabViewModel

    private var _context: Context? = null
    private var _activity: Activity? = null
    val accountDao = AccountDao()

    override fun onPageSelected(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _context = baseContext
        _activity = this@MainTabActivity
        binding = viewDataBinding
        viewModel.navigator = this

        viewModel.getProfile()

        setContentView(R.layout.activity_main_tab)

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)

        val viewPager: ViewPager = findViewById(R.id.view_pager)

        val adapter = TabAdapter(supportFragmentManager)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }


    override fun onSuccessProfile(msg: MsgGmail) {
        accountDao.deleteLogin()
        msg.Value?.let { accountDao.addLogin(it) }
        var StoreName = accountDao.getLogin()!!.storename!!.split(' ').joinToString(" ") { it.capitalize() }
        txtStoreName.text = StoreName

//        tv_name.text = name
//        if(accountDao.getLogin()!!.dokuid.isNullOrEmpty()){
//            doku_nonactive.visibility = View.VISIBLE
//            doku_active.visibility = View.GONE
//        }else{
//            doku_nonactive.visibility = View.GONE
//            doku_active.visibility = View.VISIBLE
//        }
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMsg(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector
    override fun getBindingVariable(): Int = BR.vmMain
    override fun getLayoutId(): Int = R.layout.activity_main_tab
    override fun getViewModel(): MainTabViewModel = viewModel
}
