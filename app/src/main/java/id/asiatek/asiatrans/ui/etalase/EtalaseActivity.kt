package id.asiatek.asiatrans.ui.etalase

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.bpdsulteng.jbk.realm.dao.AccountDao
import es.dmoral.toasty.Toasty
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.databinding.ActivityEtalaseBinding
import id.asiatek.asiatrans.model.account_profile.MsgAccountProfile
import id.asiatek.asiatrans.model.account_profile.UpdateRequest
import id.asiatek.asiatrans.model.etalase.EtalaseRequest
import id.asiatek.asiatrans.model.etalase.MsgEtalaseList
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.navigator.EtalaseDetailNavigator
import id.asiatek.asiatrans.ui.base.BaseActivity
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.ui.tab_menu.MainTabActivity
import id.asiatek.asiatrans.ui.tab_menu.tab_etalase.EtalaseTabFragment
import id.asiatek.asiatrans.viewmodel.EtalaseViewModel
import kotlinx.android.synthetic.main.activity_account_profile.*
import kotlinx.android.synthetic.main.activity_etalase.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class EtalaseActivity : BaseActivity<ActivityEtalaseBinding, EtalaseViewModel>(),
    EtalaseDetailNavigator {

    private lateinit var binding: ActivityEtalaseBinding
    @Inject
    internal lateinit var viewModel: EtalaseViewModel

    private var _context: Context? = null
    private var _activity: Activity? = null
    val accountDao = AccountDao()

    companion object {
        val TAG = EtalaseActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _context = baseContext
        _activity = this@EtalaseActivity
        binding = viewDataBinding
        viewModel.navigator = this

//        viewModel.getAccount()

//        setContentView(R.layout.activity_account_profile)
//
        btnAddEtalase.setOnClickListener {
            AddEtalase()
        }
    }

    private fun AddEtalase() {
        hideKeyboard()
            var request = EtalaseRequest()
            request.name = txtEtalase.text.toString()
            request.idstore = accountDao.getLogin()?.token.toString()
            viewModel.AddEtalase(request)
    }

    override fun onSuccessAdd(msg: MsgEtalaseList) {
        if(msg.status == true){
            Toasty.success(baseContext, "Berhasil Menambahkan Etalase", Toast.LENGTH_SHORT, true).show()
//            startActivity<EtalaseTabFragment.newIns>()
            txtEtalase.setText("")
        }else{
            Toasty.error(baseContext, "Gagal Menambahkan Etalase", Toast.LENGTH_SHORT, true).show()
        }
    }

    override fun onSuccessProfile(msg: MsgGmail) {
    }

//    override fun onSuccessUpdate(msg: MsgAccountProfile) {
//        if(msg.status == true){
//            Toasty.success(baseContext, "Data berhasil disimpan", Toast.LENGTH_SHORT, true).show()
//            startActivity<MainTabActivity>()
//        }else{
//            Toasty.error(baseContext, "Data gagal disimpan", Toast.LENGTH_SHORT, true).show()
//        }
//    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMsg(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getBindingVariable(): Int = BR.vmEtalase
    override fun getLayoutId(): Int = R.layout.activity_etalase
    override fun getViewModel(): EtalaseViewModel = viewModel
}
