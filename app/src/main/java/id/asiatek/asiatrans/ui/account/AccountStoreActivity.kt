package id.asiatek.asiatrans.ui.account

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.bpdsulteng.jbk.realm.dao.AccountDao
import es.dmoral.toasty.Toasty
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.databinding.ActivityAccountStoreBinding
import id.asiatek.asiatrans.model.account_store.MsgAccountStore
import id.asiatek.asiatrans.model.account_store.UpdateRequest
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.navigator.AccountStoreNavigator
import id.asiatek.asiatrans.ui.base.BaseActivity
import id.asiatek.asiatrans.ui.tab_menu.MainTabActivity
import id.asiatek.asiatrans.viewmodel.AccountStoreViewModel
import kotlinx.android.synthetic.main.activity_account_profile.*
import kotlinx.android.synthetic.main.activity_account_store.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class AccountStoreActivity : BaseActivity<ActivityAccountStoreBinding, AccountStoreViewModel>(), AccountStoreNavigator {
    private lateinit var binding: ActivityAccountStoreBinding
    @Inject
    internal lateinit var viewModel: AccountStoreViewModel

    private var _context: Context? = null
    private var _activity: Activity? = null
    val accountDao = AccountDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _context = baseContext
        _activity = this@AccountStoreActivity
        binding = viewDataBinding
        viewModel.navigator = this

        viewModel.getAccountStore()

        setContentView(R.layout.activity_account_profile)

        btnUpdateAccountStore.setOnClickListener {
            funcUpdate()
        }
    }

    override fun onSuccessProfile(msg: MsgGmail) {
        accountDao.deleteLogin()
        msg.Value?.let { accountDao.addLogin(it) }

        if(accountDao.getLogin()!!.storename.isNullOrEmpty()){
            txtNoKtp.setText("Data belum dimasukan")
        }else{
            txtNoKtp.setText(accountDao.getLogin()!!.storename!!.split(' ').joinToString(" ") { it.capitalize() })
        }

        if(accountDao.getLogin()!!.bankbook.isNullOrEmpty()){
            txtNoHp.setText("Data belum dimasukan")
        }else{
            txtNoHp.setText(accountDao.getLogin()!!.bankbook!!.split(' ').joinToString(" ") { it.capitalize() })
        }

        if(accountDao.getLogin()!!.banknumber.isNullOrEmpty()){
            txtAlamat.setText("Data belum dimasukan")
        }else{
            txtAlamat.setText(accountDao.getLogin()!!.banknumber!!.split(' ').joinToString(" ") { it.capitalize() })
        }

        if(accountDao.getLogin()!!.bank.isNullOrEmpty()){
            txtNamaNpwp.setText("Data belum dimasukan")
        }else{
            txtNamaNpwp.setText(accountDao.getLogin()!!.bank!!.split(' ').joinToString(" ") { it.capitalize() })
        }
    }

    override fun onSuccessUpdate(msg: MsgAccountStore) {
        if(msg.status == true){
            Toasty.success(baseContext, "Data berhasil disimpan", Toast.LENGTH_SHORT, true).show()
            startActivity<MainTabActivity>()
        }else{
            Toasty.error(baseContext, "Data gagal disimpan", Toast.LENGTH_SHORT, true).show()
        }
    }

    private fun funcUpdate() {
        var request = UpdateRequest()
        request.storename = txtNamaToko.text?.trim().toString()
        request.bankbook = txtBukuRekening.text?.trim().toString()
        request.banknumber = txtNoBank.text?.trim().toString()
        request.bank = txtNamaBank.text.toString()
        viewModel.updateData(request)
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMsg(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getBindingVariable(): Int = BR.vmAccountStore
    override fun getLayoutId(): Int = R.layout.activity_account_store
    override fun getViewModel(): AccountStoreViewModel = viewModel
}
