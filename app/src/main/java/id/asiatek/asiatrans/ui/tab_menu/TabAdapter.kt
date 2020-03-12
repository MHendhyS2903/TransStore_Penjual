package id.asiatek.asiatrans.ui.tab_menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.asiatek.asiatrans.ui.tab_menu.tab_home.HomeTabFragment
import id.asiatek.asiatrans.ui.tab_menu.tab_inbox.InboxTabFragment
import id.asiatek.asiatrans.ui.tab_menu.tab_item.ItemTabFragment

class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> HomeTabFragment.newInstance()
        1 -> ItemTabFragment.newInstance()
        2 -> InboxTabFragment.newInstance()
        else -> null!!
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Toko"
        1 -> "Barang"
        2 -> "Inbox"
        else -> ""
    }

    override fun getCount(): Int = 3
}