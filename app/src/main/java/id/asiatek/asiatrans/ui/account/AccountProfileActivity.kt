package id.asiatek.asiatrans.ui.account

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bpdsulteng.jbk.realm.dao.AccountDao
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.databinding.ActivityAccountProfileBinding
import id.asiatek.asiatrans.databinding.ActivityMainTabBinding
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.navigator.AccountProfileNavigator
import id.asiatek.asiatrans.navigator.MainNavigator
import id.asiatek.asiatrans.ui.base.BaseActivity
import id.asiatek.asiatrans.ui.tab_menu.TabAdapter
import id.asiatek.asiatrans.viewmodel.AccountProfileViewModel
import id.asiatek.asiatrans.viewmodel.MainTabViewModel
import kotlinx.android.synthetic.main.activity_account_profile.*
import kotlinx.android.synthetic.main.activity_main_tab.*
import javax.inject.Inject

class AccountProfileActivity : BaseActivity<ActivityAccountProfileBinding, AccountProfileViewModel>(), AccountProfileNavigator {

    private lateinit var binding: ActivityAccountProfileBinding
    @Inject
    internal lateinit var viewModel: AccountProfileViewModel

    private var _context: Context? = null
    private var _activity: Activity? = null
    val accountDao = AccountDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _context = baseContext
        _activity = this@AccountProfileActivity
        binding = viewDataBinding
        viewModel.navigator = this

        viewModel.getAccount()

        setContentView(R.layout.activity_account_profile)
    }


    override fun onSuccessProfile(msg: MsgGmail) {
        accountDao.deleteLogin()
        msg.Value?.let { accountDao.addLogin(it) }
        var StoreName = accountDao.getLogin()!!.storename!!.split(' ').joinToString(" ") { it.capitalize() }
        txtPemilik.setText(StoreName)

        if(accountDao.getLogin()!!.ktp.isNullOrEmpty()){
            txtNoKtp.setText("Belum belum dimasukan")
        }else{
            txtNoKtp.setText(accountDao.getLogin()!!.ktp!!.split(' ').joinToString(" ") { it.capitalize() })
        }

        if(accountDao.getLogin()!!.hp1.isNullOrEmpty()){
            txtNoHp.setText("Belum belum dimasukan")
        }else{
            txtNoHp.setText(accountDao.getLogin()!!.hp1!!.split(' ').joinToString(" ") { it.capitalize() })
        }

        if(accountDao.getLogin()!!.address.isNullOrEmpty()){
            txtAlamat.setText("Belum belum dimasukan")
        }else{
            txtAlamat.setText(accountDao.getLogin()!!.address!!.split(' ').joinToString(" ") { it.capitalize() })
        }

        if(accountDao.getLogin()!!.npwp.isNullOrEmpty()){
            txtNamaNpwp.setText("Belum belum dimasukan")
        }else{
            txtNamaNpwp.setText(accountDao.getLogin()!!.npwp!!.split(' ').joinToString(" ") { it.capitalize() })
        }

        if(accountDao.getLogin()!!.npwpnumber.isNullOrEmpty()){
            txtNoNpwp.setText("Belum belum dimasukan")
        }else{
            txtNoNpwp.setText(accountDao.getLogin()!!.npwpnumber!!.split(' ').joinToString(" ") { it.capitalize() })
        }
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMsg(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getBindingVariable(): Int = BR.vmAccountProfile
    override fun getLayoutId(): Int = R.layout.activity_account_profile
    override fun getViewModel(): AccountProfileViewModel = viewModel
}
