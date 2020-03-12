package id.asiatek.asiatrans.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import es.dmoral.toasty.Toasty
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.databinding.ActivityRegisterBinding
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.model.login.MsgArea
import id.asiatek.asiatrans.model.register.MsgRegister
import id.asiatek.asiatrans.model.register.RegisterRequest
import id.asiatek.asiatrans.navigator.LoginNavigator
import id.asiatek.asiatrans.ui.base.BaseActivity
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(), LoginNavigator, View.OnFocusChangeListener {

    private lateinit var binding: ActivityRegisterBinding

    @Inject
    internal lateinit var viewModel: RegisterViewModel
    private var trigerDaftar : Boolean = false
    private var trigerMasuk : Boolean = false

    companion object {
        val TAG = RegisterActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding
        viewModel.navigator = this

        tv_login.setOnClickListener {
            startActivity<LoginActivity>()
        }

        btnDaftar.setOnClickListener {
            funcRegister()
        }
    }

    private fun funcRegister() {
        if(txtNomor.text != null || txtPasswordBaru.text != null){
            if(txtConfPassword.text != txtPasswordBaru){
                Toasty.error(baseContext, "Konfirmasi Password Tidak Sesuai", Toast.LENGTH_SHORT, true).show()
            }else{
                var request = RegisterRequest()
                request.hp1 = txtNomor.text.trim().toString()
                request.password = txtPasswordBaru.text.trim().toString()
                viewModel.registerData(request)
            }
        }else{
            Toasty.error(baseContext, "Data yang anda masukan belum lengkap", Toast.LENGTH_SHORT, true).show()
        }
    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessLogin(msg: MsgGmail) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessRegister(msg: MsgRegister) {
        if(msg.status == true){
            Toast.makeText(this,"Registrasi Berhasil", Toast.LENGTH_SHORT ).show()
            startActivity<LoginActivity>()
        }else{
            Toast.makeText(this,"Your Registration is Failded", Toast.LENGTH_SHORT ).show()
        }
    }

    override fun onSuccessGmail(msg: MsgGmail) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessArea(msg: MsgArea) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onErrorLogin() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMsg(msg: String) {
    }

    override fun getBindingVariable() = BR.vmRegister
    override fun getLayoutId() = R.layout.activity_register
    override fun getViewModel() = viewModel

}
