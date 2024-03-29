package id.asiatek.asiatrans.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bpdsulteng.jbk.realm.dao.AccountDao
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import es.dmoral.toasty.Toasty
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.data.prefs.SharedPref
import id.asiatek.asiatrans.databinding.ActivityLoginBinding
import id.asiatek.asiatrans.model.gmail.GmailRequest
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.model.login.LoginRequest
import id.asiatek.asiatrans.model.login.MsgArea
import id.asiatek.asiatrans.model.login.SelectionModel
import id.asiatek.asiatrans.model.register.MsgRegister
import id.asiatek.asiatrans.navigator.LoginNavigator
import id.asiatek.asiatrans.ui.SharedPreference
import id.asiatek.asiatrans.ui.base.BaseActivity
import id.asiatek.asiatrans.ui.register.RegisterActivity
import id.asiatek.asiatrans.ui.tab_menu.MainTabActivity
import id.asiatek.asiatrans.viewmodel.LoginViewModel
import id.asiatek.asiatrans.widget.SpinnerDialog
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import java.util.*
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator, View.OnFocusChangeListener {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    internal lateinit var viewModel: LoginViewModel

    private var accountDao = AccountDao()
    private var gmailData = GmailRequest()
    private val RC_SIGN_IN = 9001
    private var googleSignInClient : GoogleSignInClient? = null
    private var spinnerDialog : SpinnerDialog? = null
    private var items = ArrayList<SelectionModel>()
    private var trigerDaftar : Boolean = false
    private var trigerMasuk : Boolean = false
    private var stsBtnMasuk : Boolean = false
    private var stsBtnDaftar : Boolean = false
    private var selected_city = String()

    /* SIGN IN */
    private var EtMasukHP : Boolean = false
    private var EtMasukPass : Boolean = false

    /* REGISTER */
    private var EtDaftarNama : Boolean = false
    private var EtDaftarEmail : Boolean = false
    private var EtDaftarHp : Boolean = false
    private var EtDaftarPass : Boolean = false
    private var EtDaftarPassConfirm : Boolean = false
    private var EtDaftarDomisili : Boolean = false
    private var mGuideView: GuideView? = null
    private var builder: GuideView.Builder? = null

    companion object {
        val TAG = LoginActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding
        viewModel.navigator = this

        btnLogin.setOnClickListener {
            funcLogin()
        }
    }

    private fun funcLogin() {
        if(txtPhone != null || txtPassword != null){
            var request = LoginRequest()
            request.cellular = txtPhone.text.trim().toString()
            request.password = txtPassword.text.trim().toString()
            viewModel.login(request)
        }else{
            Toasty.error(baseContext, "Masukan Nomor Handphone dan Password", Toast.LENGTH_SHORT, true).show()
        }
    }

    override fun onSuccessLogin(msg: MsgGmail) {
        if(msg.Status == true){
            msg.Value?.let { accountDao.addLogin(it) }
            SharedPref.setToken(accountDao.getLoginToken())
            finish()
            startActivity<MainTabActivity>()
        }else{
            Toasty.error(baseContext, "Nomor Handphone atau Password Salah", Toast.LENGTH_SHORT, true).show()
        }
    }

    override fun onSuccessRegister(msg: MsgRegister) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessGmail(msg: MsgGmail) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessArea(msg: MsgArea) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onErrorLogin() {
    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMsg(msg: String) {}

    override fun onBackPressed() {
        if(trigerMasuk || trigerDaftar) {

        }else{
            super.onBackPressed()
            overridePendingTransitionExit()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    protected fun overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    protected fun overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun getBindingVariable() = BR.vmLogin
    override fun getLayoutId() = R.layout.activity_login
    override fun getViewModel() = viewModel
}
