package id.asiatek.asiatrans.ui.menu

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.BR
import id.asiatek.asiatrans.databinding.ActivityMenuManualBinding
import id.asiatek.asiatrans.model.account.MsgAccount
import id.asiatek.asiatrans.model.cart.MsgCart
import id.asiatek.asiatrans.model.item.MsgItem
import id.asiatek.asiatrans.model.wishlist.MsgWishlist
import id.asiatek.asiatrans.navigator.MenuNavigator
import id.asiatek.asiatrans.ui.base.BaseActivity
import id.asiatek.asiatrans.ui.menu.ui.account.AccountFragment
import id.asiatek.asiatrans.ui.menu.ui.cart.CartFragment
import id.asiatek.asiatrans.ui.menu.ui.favorite.FavoriteFragment
import id.asiatek.asiatrans.ui.menu.ui.home.HomeFragment
import id.asiatek.asiatrans.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.activity_menu_manual.*
import javax.inject.Inject

class MenuActivity : BaseActivity<ActivityMenuManualBinding, MenuViewModel>(), MenuNavigator,
    HasSupportFragmentInjector {
    override fun onSuccessAccount(msg: MsgAccount) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessCart(msg: MsgCart) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessWishlist(msg: MsgWishlist) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    private lateinit var binding: ActivityMenuManualBinding

    @Inject
    internal lateinit var viewModel: MenuViewModel


//    private var content: FrameLayout? = null
    private var _context: Context? = null
    private var _activity: Activity? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.homeFragment -> {
                val fragment = HomeFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.favoriteFragment -> {
                val fragment = FavoriteFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.cartFragment -> {
                val fragment = CartFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.accountFragment -> {
                val fragment = AccountFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AndroidInjection.inject(this)
//        setContentView(R.layout.activity_menu_manual)

        _context = baseContext
        _activity = this@MenuActivity
        binding = viewDataBinding
        viewModel.navigator = this

//        view_pager.adapter = PagerAdapter(supportFragmentManager)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = HomeFragment.newInstance()
        addFragment(fragment)

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment)
            .commit()
    }

    override fun onBackPressed()
    {
        finishAffinity()
    }

    override fun onSuccessDisplay(msg: MsgItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onErrorDisplay() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMsg(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector
    override fun getBindingVariable(): Int = BR.vmMenu
    override fun getLayoutId(): Int = R.layout.activity_menu_manual
    override fun getViewModel(): MenuViewModel = viewModel

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//
//        val inflater = menuInflater
//        inflater.inflate(R.menu.menu_search, menu)
//
//        val manger = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchItem = menu?.findItem(R.id.action_search)
//        val searchView = searchItem?.actionView as androidx.appcompat.widget.SearchView
//        return true
//    }
}
