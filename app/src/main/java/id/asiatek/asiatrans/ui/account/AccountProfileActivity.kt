package id.asiatek.asiatrans.ui.account

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bpdsulteng.jbk.realm.dao.AccountDao
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import es.dmoral.toasty.Toasty
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.databinding.ActivityAccountProfileBinding
import id.asiatek.asiatrans.databinding.ActivityMainTabBinding
import id.asiatek.asiatrans.model.account_profile.MsgAccountProfile
import id.asiatek.asiatrans.model.account_profile.UpdateRequest
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.model.register.MsgRegister
import id.asiatek.asiatrans.model.register.RegisterRequest
import id.asiatek.asiatrans.navigator.AccountProfileNavigator
import id.asiatek.asiatrans.navigator.MainNavigator
import id.asiatek.asiatrans.ui.base.BaseActivity
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.ui.tab_menu.MainTabActivity
import id.asiatek.asiatrans.ui.tab_menu.TabAdapter
import id.asiatek.asiatrans.viewmodel.AccountProfileViewModel
import id.asiatek.asiatrans.viewmodel.MainTabViewModel
import kotlinx.android.synthetic.main.activity_account_profile.*
import kotlinx.android.synthetic.main.activity_main_tab.*
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
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

        btnUpdateAccountProfile.setOnClickListener {
            funcUpdate()
        }
    }

    override fun onSuccessProfile(msg: MsgGmail) {
        accountDao.deleteLogin()
        msg.Value?.let { accountDao.addLogin(it) }
        var StoreName = accountDao.getLogin()!!.storename!!.split(' ').joinToString(" ") { it.capitalize() }
        txtPemilik.setText(StoreName)

        if(accountDao.getLogin()!!.ktp.isNullOrEmpty()){
            txtNoKtp.setText("Data belum dimasukan")
        }else{
            txtNoKtp.setText(accountDao.getLogin()!!.ktp!!.split(' ').joinToString(" ") { it.capitalize() })
        }

        if(accountDao.getLogin()!!.hp1.isNullOrEmpty()){
            txtNoHp.setText("Data belum dimasukan")
        }else{
            txtNoHp.setText(accountDao.getLogin()!!.hp1!!.split(' ').joinToString(" ") { it.capitalize() })
        }

        if(accountDao.getLogin()!!.address.isNullOrEmpty()){
            txtAlamat.setText("Data belum dimasukan")
        }else{
            txtAlamat.setText(accountDao.getLogin()!!.address!!.split(' ').joinToString(" ") { it.capitalize() })
        }

        if(accountDao.getLogin()!!.npwp.isNullOrEmpty()){
            txtNamaNpwp.setText("Data belum dimasukan")
        }else{
            txtNamaNpwp.setText(accountDao.getLogin()!!.npwp!!.split(' ').joinToString(" ") { it.capitalize() })
        }

        if(accountDao.getLogin()!!.npwpnumber.isNullOrEmpty()){
            txtNoNpwp.setText("Data belum dimasukan")
        }else{
            txtNoNpwp.setText(accountDao.getLogin()!!.npwpnumber!!.split(' ').joinToString(" ") { it.capitalize() })
        }
    }

    override fun onSuccessUpdate(msg: MsgAccountProfile) {
        if(msg.status == true){
            Toasty.success(baseContext, "Data berhasil disimpan", Toast.LENGTH_SHORT, true).show()
            startActivity<MainTabActivity>()
        }else{
            Toasty.error(baseContext, "Data gagal disimpan", Toast.LENGTH_SHORT, true).show()
        }
    }

    private fun funcUpdate() {
        var request = UpdateRequest()
        request.hp1 = txtPemilik.text.toString()
        request.ktp = txtNoKtp.text?.trim().toString()
        request.hp1 = txtNoHp.text?.trim().toString()
        request.address = txtAlamat.text.toString()
        request.npwp = txtNamaNpwp.text.toString()
        request.npwpnumber = txtAlamat.text?.trim().toString()
        viewModel.updateData(request)
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
