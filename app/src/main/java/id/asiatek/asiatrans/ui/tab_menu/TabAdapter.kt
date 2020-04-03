package id.asiatek.asiatrans.ui.tab_menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.asiatek.asiatrans.ui.tab_menu.tab_etalase.EtalaseTabFragment
import id.asiatek.asiatrans.ui.tab_menu.tab_home.HomeTabFragment
import id.asiatek.asiatrans.ui.tab_menu.tab_order.OrderTabFragment
import id.asiatek.asiatrans.ui.tab_menu.tab_item.ItemTabFragment

class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> HomeTabFragment.newInstance()
        1-> EtalaseTabFragment.newInstance()
        2 -> ItemTabFragment.newInstance()
        3 -> OrderTabFragment.newInstance()
        else -> null!!
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Toko"
        1->  "Etalase"
        2 -> "Barang"
        3 -> "Pesanan"
        else -> ""
    }

    override fun getCount(): Int = 4
}