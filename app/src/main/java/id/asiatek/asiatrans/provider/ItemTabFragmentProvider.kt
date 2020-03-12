package id.asiatek.asiatrans.provider

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.asiatek.asiatrans.module.HomeTabFragmentModule
import id.asiatek.asiatrans.module.ItemTabFragmentModule
import id.asiatek.asiatrans.ui.tab_menu.tab_home.HomeTabFragment
import id.asiatek.asiatrans.ui.tab_menu.tab_item.ItemTabFragment

@Module
abstract class ItemTabFragmentProvider {
    @ContributesAndroidInjector(modules = [ItemTabFragmentModule::class])
    abstract fun provideItemTabFragmentFactory(): ItemTabFragment
}