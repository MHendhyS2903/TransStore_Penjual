package id.asiatek.asiatrans.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.asiatek.asiatrans.ui.menu.ui.account.AccountFragment
import id.asiatek.asiatrans.ui.menu.ui.cart.CartFragment
import id.asiatek.asiatrans.ui.menu.ui.favorite.FavoriteFragment
import id.asiatek.asiatrans.ui.menu.ui.home.HomeFragment

class PagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    // sebuah list yang menampung objek Fragment
    private val pages = listOf(
        FavoriteFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Home Tab"
            1 -> "Favorite Tab"
            2-> "Favorite Tab"
            else -> "Fourth Tab"
        }
    }
}