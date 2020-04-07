package id.asiatek.asiatrans.ui.tab_menu.tab_item

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bpdsulteng.jbk.realm.dao.AccountDao
import es.dmoral.toasty.Toasty
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.databinding.ActivityProductBinding
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.model.item.*
import id.asiatek.asiatrans.navigator.ItemNavigator
import id.asiatek.asiatrans.ui.base.BaseActivity
import id.asiatek.asiatrans.utils.AppConstants
import id.asiatek.asiatrans.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_product.*
import javax.inject.Inject

class ProductActivity : BaseActivity<ActivityProductBinding, ProductViewModel>(), ItemNavigator, View.OnFocusChangeListener {

    private lateinit var binding: ActivityProductBinding

    @Inject
    internal lateinit var viewModel: ProductViewModel

    private var accountDao = AccountDao()
    private var trigerDaftar : Boolean = false
    private var trigerMasuk : Boolean = false

    companion object {
        val TAG = ProductActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding
        viewModel.navigator = this

        setupView()

    }

    private fun setupView() {
        if (intent.getParcelableExtra(AppConstants.KEY_REQUEST) as? DataItem == null) {
            txtNamaProduct.setText("")
            txtHargaProduct.setText("")
            txtDeskripsiProduct.setText("")
            btnSaveProduct.setText("Tambah Produk")

            btnSaveProduct.setOnClickListener {
                funcAddProduct()
            }

        } else {
            viewModel.title = getRequest().title.toString().split(' ').joinToString(" ") { it.capitalize() }
            viewModel.id = getRequest().id.toString()
            viewModel.price = getRequest().price.toString()
            viewModel.description = getRequest().description.toString()
            txtNamaProduct.setText(getRequest().title.toString().split(' ').joinToString(" "))
            txtHargaProduct.setText(getRequest().price.toString())
            txtDeskripsiProduct.setText(getRequest().description.toString())
            btnSaveProduct.setText("Ubah Produk")

            btnSaveProduct.setOnClickListener {
                funcUpdateProduct()
            }

        }
    }

    private fun funcAddProduct() {
        val harga = txtHargaProduct.text.toString()
        var request = AddProductRequest()
        request.title = txtNamaProduct.text.toString()
        request.price = harga.toDouble()
        request.description = txtDeskripsiProduct.text.toString()
        request.store_token = accountDao.getLoginToken().toString()

        viewModel.AddProduct(request)
    }

    private fun funcUpdateProduct() {
        val harga = txtHargaProduct.text.toString()
        var request = UpdateProductRequest()
//        request.id = getRequest().id.toString()
        request.title = txtNamaProduct.text.toString()
        request.price = harga.toDouble()
        request.description = txtDeskripsiProduct.text.toString()

        viewModel.updateProduct(request)
    }

    override fun onSuccessAdd(msg: MsgItem) {
        if(msg.status == true){
            Toasty.success(baseContext, "Registrasi Berhasil", Toast.LENGTH_SHORT, true).show()
//            val intent = Intent (this, ItemTabFragment::class.java)
//            startActivity(intent)
        }else{
            Toasty.error(baseContext, "Registrasi Gagal", Toast.LENGTH_SHORT, true).show()
        }
    }

    override fun onSuccessUpdate(msg: MsgItem) {
        if(msg.status == true){
            Toasty.success(baseContext, "Berhasil Merubah Produk", Toast.LENGTH_SHORT, true).show()
//            startActivity<EtalaseTabFragment.newIns>()
//            txtEtalase.setText("")
        }else{
            Toasty.error(baseContext, "Gagal Merubah Produk", Toast.LENGTH_SHORT, true).show()
        }
    }

    override fun onSuccess() {
//        if(msg.status == true){
            Toasty.success(baseContext, "Berhasil Merubah Produk", Toast.LENGTH_SHORT, true).show()
//            startActivity<EtalaseTabFragment.newIns>()
//            txtEtalase.setText("")
//        }else{
//            Toasty.error(baseContext, "Gagal Merubah Produk", Toast.LENGTH_SHORT, true).show()
//        }
    }

    override fun onSuccessProfile(msg: MsgGmail) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMsg(msg: String) {
    }

    private fun getRequest(): DataItem = intent.getParcelableExtra(AppConstants.KEY_REQUEST) as DataItem
    override fun getBindingVariable() = BR.vmProduct
    override fun getLayoutId() = R.layout.activity_product
    override fun getViewModel() = viewModel

}
