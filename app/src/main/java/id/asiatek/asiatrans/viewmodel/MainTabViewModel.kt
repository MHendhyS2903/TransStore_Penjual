package id.asiatek.asiatrans.viewmodel

import androidx.viewpager.widget.ViewPager
import id.asiatek.asiatrans.navigator.MainNavigator
import id.asiatek.asiatrans.ui.base.BaseObservableViewModel

class MainTabViewModel : BaseObservableViewModel<MainNavigator>() {
    inner class ViewPagerSlideListener : ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            navigator.onPageSelected(position)
        }
    }
}