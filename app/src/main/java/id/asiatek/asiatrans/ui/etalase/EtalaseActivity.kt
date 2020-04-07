package id.asiatek.asiatrans.ui.etalase

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.bpdsulteng.jbk.realm.dao.AccountDao
import com.bumptech.glide.Glide
import es.dmoral.toasty.Toasty
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.databinding.ActivityEtalaseBinding
import id.asiatek.asiatrans.model.account_profile.MsgAccountProfile
import id.asiatek.asiatrans.model.account_profile.UpdateRequest
import id.asiatek.asiatrans.model.etalase.DataItemEtalase
import id.asiatek.asiatrans.model.etalase.EtalaseRequest
import id.asiatek.asiatrans.model.etalase.MsgEtalase
import id.asiatek.asiatrans.model.etalase.MsgEtalaseList
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.navigator.EtalaseDetailNavigator
import id.asiatek.asiatrans.ui.base.BaseActivity
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.ui.tab_menu.MainTabActivity
import id.asiatek.asiatrans.ui.tab_menu.tab_etalase.EtalaseTabFragment
import id.asiatek.asiatrans.utils.AppConstants.KEY_REQUEST
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
//
        btnAddEtalase.setOnClickListener {
            if(btnAddEtalase.text=="Tambah Data")
            {
                AddEtalase()
            }else{
                UpdateEtalase()
            }
        }

        setupView()
    }

    private fun setupView() {

        if (intent.getParcelableExtra(KEY_REQUEST) as? DataItemEtalase == null) {
            txtEtalase.setText("")
            btnAddEtalase.setText("Tambah Data")
        } else {
            viewModel.title = getRequest().name.toString().split(' ').joinToString(" ") { it.capitalize() }
            viewModel.id = getRequest().id.toString()
            txtEtalase.setText(getRequest().name.toString().split(' ').joinToString(" "))
            btnAddEtalase.setText("Simpan Data")
        }
    }

    private fun AddEtalase() {
        hideKeyboard()
            var request = EtalaseRequest()
            request.name = txtEtalase.text.toString()
            request.idstore = accountDao.getLogin()?.token.toString()
            viewModel.AddEtalase(request)
    }

    private fun UpdateEtalase() {
        hideKeyboard()
        var request = EtalaseRequest()
        request.name = txtEtalase.text.toString()
        viewModel.updateEtalase(request)
    }

    private fun DeleteEtalase() {
        hideKeyboard()
        viewModel.deleteEtalase()
    }

    override fun onSuccessAdd(msg: MsgEtalase) {
        if(msg.status == true){
            Toasty.success(baseContext, "Berhasil Menambahkan Etalase", Toast.LENGTH_SHORT, true).show()
//            startActivity<EtalaseTabFragment.newIns>()
            txtEtalase.setText("")
        }else{
            Toasty.error(baseContext, "Gagal Menambahkan Etalase", Toast.LENGTH_SHORT, true).show()
        }
    }

    override fun onSuccessUpdate(msg: MsgEtalase) {
        if(msg.status == true){
            Toasty.success(baseContext, "Berhasil Merubah Etalase", Toast.LENGTH_SHORT, true).show()
//            startActivity<EtalaseTabFragment.newIns>()
            txtEtalase.setText("")
        }else{
            Toasty.error(baseContext, "Gagal Merubah Etalase", Toast.LENGTH_SHORT, true).show()
        }
    }

    override fun onSuccess() {
        Toasty.success(baseContext, "Berhasil Merubah Etalase", Toast.LENGTH_SHORT, true).show()
    }

    override fun onSuccessDelete() {
        Toasty.success(baseContext, "Berhasil Menghapus Etalase", Toast.LENGTH_SHORT, true).show()
//            startActivity<EtalaseTabFragment.newIns>()
        txtEtalase.setText("")
    }

    override fun onSuccessProfile(msg: MsgGmail) {
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMsg(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    private fun getRequest(): DataItemEtalase = intent.getParcelableExtra(KEY_REQUEST) as DataItemEtalase
    override fun getBindingVariable(): Int = BR.vmEtalase
    override fun getLayoutId(): Int = R.layout.activity_etalase
    override fun getViewModel(): EtalaseViewModel = viewModel
}
